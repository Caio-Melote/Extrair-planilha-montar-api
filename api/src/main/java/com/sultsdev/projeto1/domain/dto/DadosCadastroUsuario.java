package com.sultsdev.projeto1.domain.dto;

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
public class DadosCadastroUsuario {
	
	@NotBlank
	private String login;
	
	@NotBlank
	private String senha;
	
	@NotNull
	private Boolean ativo;
	
	@NotBlank
	private String role;
	
	
}
