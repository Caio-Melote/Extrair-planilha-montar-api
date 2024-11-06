package com.sultsdev.projeto1.domain.dto;

import com.sultsdev.projeto1.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsuarioListagem {
	
	private Long id;
	private String login;
	
	public UsuarioListagem(Usuario user) {
		this.id = user.getId();
		this.login = user.getLogin();
	}
}
