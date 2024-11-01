package com.sultsdev.projeto1.domain.dto;

import java.time.LocalDateTime;

import com.sultsdev.projeto1.model.Segmento;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosAtualizacaoFranquia {
	
	@NotNull
	private Long id;	
	private String nome;
	private Integer totalUnidades;
	private String estadoSede;
	private String email;
	private Double investimentoInicial;
	private String subsegmento;
	private String tipoNegocio;
	private LocalDateTime ultimaAtualizacao;
	private String url;
	private Boolean ativo;
	private Segmento segmento;

}
