package com.sultsdev.projeto1.domain.dto;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosAtualizacaoUsuario {
	
	@Null
	private Long id;
	private String login;
	private String senha;
	private String role;
	private Boolean ativo;
}
