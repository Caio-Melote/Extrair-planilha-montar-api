package com.sultsdev.projeto1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sultsdev.projeto1.service.LeituraPlanilha;

@RestController
@RequestMapping("/popularbanco")
public class PopularBancoController {
	
	@Autowired
	LeituraPlanilha planilha;
	
	
	@GetMapping
	private String populando() {
		
		planilha.lerPlanilha();
		
		return "<h1>Banco populado com sucesso!</h1>";
	}
}
