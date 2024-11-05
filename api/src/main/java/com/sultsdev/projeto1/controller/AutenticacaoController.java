package com.sultsdev.projeto1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sultsdev.projeto1.domain.dto.DadosAutenticacao;
import com.sultsdev.projeto1.domain.dto.DadosTokenJWT;
import com.sultsdev.projeto1.infraestrutura.security.TokenService;
import com.sultsdev.projeto1.model.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {		
		
		var authToken = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());	
		var authentication = manager.authenticate(authToken);	
		
		var tokenJWT = tokenService.geraToken((Usuario) authentication.getPrincipal());
		
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
		
	}
}
