package com.sultsdev.projeto1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sultsdev.projeto1.model.Segmento;

public interface SegmentoRepository extends JpaRepository<Segmento, Long> {
	Segmento findByNome(String nome);
}
