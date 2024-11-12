package com.sultsdev.projeto1.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sultsdev.projeto1.domain.dto.DadoSemPaginacao;
import com.sultsdev.projeto1.domain.dto.DadosAtualizacaoFranquia;
import com.sultsdev.projeto1.domain.dto.DadosCadastroFranquia;
import com.sultsdev.projeto1.domain.dto.DadosRespostaPaginada;
import com.sultsdev.projeto1.domain.dto.FranquiaListagem;
import com.sultsdev.projeto1.model.Franquia;
import com.sultsdev.projeto1.model.Segmento;
import com.sultsdev.projeto1.repository.FranquiaRepository;
import com.sultsdev.projeto1.repository.SegmentoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("franquia")
public class FranquiaController {

	@Autowired
	private FranquiaRepository repository;

	@Autowired
	private SegmentoRepository segmentoRepository;

	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity listar(@PageableDefault(size = 20) Pageable paginacao,
			@RequestParam(required = false) String nome, @RequestParam(required = false) String segmento,
			@RequestParam(required = false) String estadoSede,
			@RequestParam(required = false) BigDecimal investimentoInicialStart,
			@RequestParam(required = false) BigDecimal investimentoInicialEnd,
			@RequestParam(required = false) LocalDateTime dataUltimaAtualizacaoStart,
			@RequestParam(required = false) LocalDateTime dataUltimaAtualizacaoEnd) {
		var page = repository.findAllByFilters(nome, segmento, estadoSede, investimentoInicialStart,
				investimentoInicialEnd, dataUltimaAtualizacaoStart, dataUltimaAtualizacaoEnd, paginacao)
				.map(FranquiaListagem::new);

		DadosRespostaPaginada<FranquiaListagem> response = new DadosRespostaPaginada<>(page);
		return ResponseEntity.ok(response);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFranquia dados, UriComponentsBuilder uriBuilder) {
		Segmento segmentoExistenteNome = segmentoRepository.findByNome(dados.getSegmento().getNome());

		Segmento segmento;
		if (segmentoExistenteNome != null) {
			segmento = segmentoExistenteNome;
		} else {
			segmento = new Segmento(dados.getSegmento()); // .getNome()
			segmentoRepository.save(segmento);
		}

		var franquia = new Franquia(dados);
		franquia.setSegmento(segmento);
		repository.save(franquia);

		var uri = uriBuilder.path("/franquia/{id}").buildAndExpand(franquia.getId()).toUri();

		var resposta = ResponseEntity.created(uri).body(new DadoSemPaginacao(franquia));

		return resposta;
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoFranquia dados) {
		
		var franquiaOptional = repository.findById(id);
//		if (franquiaOptional.isEmpty()) {
//			return ResponseEntity.notFound().build();
//		}
		
		Segmento segmentoExistenteNome = segmentoRepository.findByNome(dados.getSegmento().getNome());
		Segmento segmentoExistenteId = segmentoRepository.findSegmentoById(dados.getSegmento().getId());

		if (dados.getSegmento().getId() == null && dados.getSegmento().getNome() == null) {
			return ResponseEntity.badRequest().body("Segmento não informado");
		}

		if (segmentoExistenteNome != null && segmentoExistenteId != null) {
			if (segmentoExistenteNome.getId() != segmentoExistenteId.getId()) {
				return ResponseEntity.badRequest().body("Nome e Id divergentes, informe apenas o nome!");
			}
		}

		if (segmentoExistenteNome != null && dados.getSegmento().getId() != null) {
			return ResponseEntity.badRequest().body("ID inexsistente, informe apenas o nome!");
		}

		if (segmentoExistenteId != null && dados.getSegmento().getNome() != null) {
			return ResponseEntity.badRequest().body("Nome inexsistente, mas o ID existe.");
		}

		if ((dados.getSegmento().getId() != null && dados.getSegmento().getNome() != null)
				&& (segmentoExistenteNome == null && segmentoExistenteId == null)) {
			return ResponseEntity.badRequest().body("Nome inexistente / ID inexistente");
		}

		if (dados.getSegmento().getId() != null && segmentoExistenteId == null
				&& dados.getSegmento().getNome() == null) {
			return ResponseEntity.badRequest().body("ID inexistente e nome não informado");
		}

		var franquia = franquiaOptional.get();
		franquia.atualizarInformacoes(dados, segmentoRepository);

		repository.save(franquia);

		var resposta = new DadoSemPaginacao(franquia);

		return ResponseEntity.ok(resposta);
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		
			var franquia = repository.getReferenceById(id);
			franquia.excluir();
			return ResponseEntity.noContent().build();	
	}


}
