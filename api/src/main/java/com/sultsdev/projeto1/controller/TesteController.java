package com.sultsdev.projeto1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sultsdev.projeto1.service.LeituraPlanilha;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	LeituraPlanilha planilhaTeste;
	
	
	@GetMapping
	private String testando() {
		
		planilhaTeste.lerPlanilha();
		
		return "<h1>!Primeiro teste!</h1>";
	}
}
