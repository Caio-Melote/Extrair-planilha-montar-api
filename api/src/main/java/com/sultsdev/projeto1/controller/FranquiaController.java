package com.sultsdev.projeto1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sultsdev.projeto1.domain.dto.FranquiaListagem;
import com.sultsdev.projeto1.repository.FranquiaRepository;

@RestController
@RequestMapping("franquia")
public class FranquiaController {
	
	@Autowired
    private FranquiaRepository repository;
	
	@GetMapping
    public ResponseEntity<Page<FranquiaListagem>> listar(@PageableDefault(size = 20) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(FranquiaListagem::new);
        return ResponseEntity.ok(page);
    }
	
	 //@GetMapping("/{nome}")
}
