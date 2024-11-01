package com.sultsdev.projeto1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "segmentos")
@Entity(name = "Segmento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Segmento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
		
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void atualizarInformacoes(Segmento segmento) {
		if (segmento.getNome() != null) {
            this.nome = segmento.getNome();
        }
	}
	
	public Segmento (Segmento dados) {
		this.nome = dados.nome;
	}
	
	
}
