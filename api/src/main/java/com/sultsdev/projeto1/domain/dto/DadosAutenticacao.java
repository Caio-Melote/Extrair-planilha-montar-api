package com.sultsdev.projeto1.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosAutenticacao {
	
	private String login;
	private String senha;
}
