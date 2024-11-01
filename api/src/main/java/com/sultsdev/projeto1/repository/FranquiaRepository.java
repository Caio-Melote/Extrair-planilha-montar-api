package com.sultsdev.projeto1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sultsdev.projeto1.model.Franquia;

public interface FranquiaRepository extends JpaRepository<Franquia, Long> {
	Page<Franquia> findAllByAtivoTrue(Pageable paginacao);
	
}
