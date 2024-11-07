package com.sultsdev.projeto1.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sultsdev.projeto1.model.Franquia;

public interface FranquiaRepository extends JpaRepository<Franquia, Long> {
	Page<Franquia> findAllByAtivoTrue(Pageable paginacao);

	@Query("SELECT f FROM Franquia f " + "WHERE (:nome IS NULL OR f.nome LIKE %:nome%) "
			+ "AND (:segmento IS NULL OR f.segmento.nome LIKE %:segmento%) "
			+ "AND (:estadoSede IS NULL OR f.estadoSede LIKE %:estadoSede%) "
			+ "AND (:investimentoInicialStart IS NULL OR f.investimentoInicial >= :investimentoInicialStart) "
			+ "AND (:investimentoInicialEnd IS NULL OR f.investimentoInicial <= :investimentoInicialEnd) "
			+ "AND (:dataUltimaAtualizacaoStart IS NULL OR f.ultimaAtualizacao >= :dataUltimaAtualizacaoStart) "
			+ "AND (:dataUltimaAtualizacaoEnd IS NULL OR f.ultimaAtualizacao <= :dataUltimaAtualizacaoEnd) "
			+ "AND f.ativo = TRUE")
	Page<Franquia> findAllByFilters(@Param("nome") String nome, 
			@Param("segmento") String segmento,
			@Param("estadoSede") String estadoSede, 
			@Param("investimentoInicialStart") Double investimentoInicialStart,
			@Param("investimentoInicialEnd") Double investimentoInicialEnd,
			@Param("dataUltimaAtualizacaoStart") LocalDateTime dataUltimaAtualizacaoStart,
			@Param("dataUltimaAtualizacaoEnd") LocalDateTime dataUltimaAtualizacaoEnd, Pageable pageable);
}
