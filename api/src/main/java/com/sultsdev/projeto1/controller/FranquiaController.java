package com.sultsdev.projeto1.controller;

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

	@GetMapping
	public ResponseEntity<DadosRespostaPaginada<FranquiaListagem>> listar(@PageableDefault(size = 20) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(FranquiaListagem::new);
		DadosRespostaPaginada<FranquiaListagem> response = new DadosRespostaPaginada<>(page);
		return ResponseEntity.ok(response);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFranquia dados, UriComponentsBuilder uriBuilder) {
		Segmento segmentoExistente = segmentoRepository.findByNome(dados.getSegmento().getNome());

		Segmento segmento;
		if (segmentoExistente != null) {
			segmento = segmentoExistente;
		} else {
			segmento = new Segmento(dados.getSegmento()); // .getNome()
			segmentoRepository.save(segmento);
		}

		var franquia = new Franquia(dados);
		franquia.setSegmento(segmento);
		repository.save(franquia);

		var uri = uriBuilder.path("/franquia/{id}").buildAndExpand(franquia.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadoSemPaginacao(franquia));
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoFranquia dados) {
		var franquiaOptional = repository.findById(dados.getId());
		if (franquiaOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		var franquia = franquiaOptional.get();
		franquia.atualizarInformacoes(dados, segmentoRepository);

		repository.save(franquia);

		return ResponseEntity.ok(new DadoSemPaginacao(franquia));
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var franquia = repository.getReferenceById(id);
		franquia.excluir();

		return ResponseEntity.noContent().build();
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity reativar(@PathVariable Long id) {
		var franquia = repository.getReferenceById(id);
		franquia.reativar();

		return ResponseEntity.noContent().build();
	}

}
