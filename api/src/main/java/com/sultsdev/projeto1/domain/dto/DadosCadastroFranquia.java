package com.sultsdev.projeto1.domain.dto;

import com.sultsdev.projeto1.model.Segmento;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosCadastroFranquia {
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Integer totalUnidades;
	
	@NotBlank
	private String estadoSede;
	
	@NotBlank
	@Email
	private String email;
	
	@NotNull
	private Double investimentoInicial;
	
	@NotBlank
	private String subsegmento;
	
	@NotBlank
	private String tipoNegocio;
	
	@NotBlank
	private String url;
	
	@NotNull
	private Boolean ativo;
	
	@NotNull
	private Segmento segmento;
}
