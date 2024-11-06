package com.sultsdev.projeto1.domain.dto;

import jakarta.validation.constraints.NotBlank;
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
	
	
}
