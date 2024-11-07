package com.sultsdev.projeto1.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosAtualizacaoUsuario {
	
	@NotNull
	private Long id;
	private String login;
	private String senha;
	private String role;
}
