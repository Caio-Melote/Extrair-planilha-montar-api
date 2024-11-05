package com.sultsdev.projeto1.domain.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.sultsdev.projeto1.model.Franquia;
import com.sultsdev.projeto1.model.Segmento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FranquiaListagem {

	private Long id;
	private String nome;
	private int totalUnidades;
	private String estadoSede;
	private String email;
	private Double investimentoInicial;
	private String subsegmento;
	private String tipoNegocio;
	private LocalDateTime ultimaAtualizacao;
	private String url;
	private Boolean ativo;
	private Segmento segmento;

	public FranquiaListagem(Franquia franquia) {
		this.id = franquia.getId();
		this.nome = franquia.getNome();
		this.totalUnidades = franquia.getTotalUnidades();
		this.estadoSede = franquia.getEstadoSede();
		this.email = franquia.getEmail();
		this.investimentoInicial = franquia.getInvestimentoInicial();
		this.subsegmento = franquia.getSubsegmento();
		this.tipoNegocio = franquia.getTipoNegocio();
		
		LocalDateTime ultimaAtualizacaoLocal = franquia.getUltimaAtualizacao();
		ZonedDateTime ultimaAtualizacaoSP = ultimaAtualizacaoLocal.atZone(ZoneId.of("America/Sao_Paulo"));
		this.ultimaAtualizacao = ultimaAtualizacaoSP.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
		
		this.url = franquia.getUrl();
		this.ativo = franquia.getAtivo();
		this.segmento = franquia.getSegmento();
	}
}
