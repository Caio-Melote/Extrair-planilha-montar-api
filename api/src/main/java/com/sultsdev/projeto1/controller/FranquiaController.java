package com.sultsdev.projeto1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sultsdev.projeto1.domain.dto.DadosCadastroFranquia;
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
    public ResponseEntity<Page<FranquiaListagem>> listar(@PageableDefault(size = 20) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(FranquiaListagem::new);
        return ResponseEntity.ok(page);
    }
	
	@PostMapping
	@Transactional
	public ResponseEntity<FranquiaListagem> cadastrar(@RequestBody @Valid DadosCadastroFranquia dados, UriComponentsBuilder uriBuilder) {	    
	    Segmento segmentoExistente = segmentoRepository.findByNome(dados.getSegmento().getNome());
	    	
	    Segmento segmento;
	    if (segmentoExistente != null) {
	        segmento = segmentoExistente;
	    } else {
	        segmento = new Segmento(dados.getSegmento()); //.getNome()
	        segmentoRepository.save(segmento); 
	    }

	    var franquia = new Franquia(dados);
	    franquia.setSegmento(segmento); 
	    repository.save(franquia);

	    var uri = uriBuilder.path("/franquia/{id}").buildAndExpand(franquia.getId()).toUri();

	    return ResponseEntity.created(uri).body(new FranquiaListagem(franquia));
	}


//    @PutMapping
//    @Transactional
//    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoFranquia dados) {
//        var franquia = repository.getReferenceById(dados.id());
//        franquia.atualizarInformacoes(dados);
//
//        return ResponseEntity.ok(new FranquiaListagem(franquia));
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var franquia = repository.getReferenceById(id);
        franquia.excluir();

        return ResponseEntity.noContent().build();
    }
	
}
