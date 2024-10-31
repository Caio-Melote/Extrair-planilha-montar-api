package com.sultsdev.projeto1.domain.dto;

import com.sultsdev.projeto1.model.Segmento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SegmentoListagem {
	
	private Long id;
	private String nome;
	
	public SegmentoListagem(Segmento segmento) {
		this.id = segmento.getId();
		this.nome = segmento.getNome();
	}
}
