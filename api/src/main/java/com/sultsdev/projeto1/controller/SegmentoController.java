package com.sultsdev.projeto1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sultsdev.projeto1.domain.dto.SegmentoListagem;
import com.sultsdev.projeto1.repository.SegmentoRepository;

@RestController
@RequestMapping("segmento")
public class SegmentoController {
	
	@Autowired
    private SegmentoRepository repository;
	
	@GetMapping
    public ResponseEntity<Page<SegmentoListagem>> listar(@PageableDefault(size = 20) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(SegmentoListagem::new);
        return ResponseEntity.ok(page);
    }
		
	@GetMapping("/{nome}")
	public ResponseEntity<Page<SegmentoListagem>> detalhar(@PathVariable String nome, Pageable pageable) {
	    var segmentos = repository.findByNomeContainingIgnoreCase(nome, pageable);
	    if (segmentos.hasContent()) {
	        return ResponseEntity.ok(segmentos.map(SegmentoListagem::new));
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

}
