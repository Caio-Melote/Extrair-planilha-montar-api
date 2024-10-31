package com.sultsdev.projeto1.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "franquias")
@Entity(name = "Franquia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Franquia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nome;
	private int totalUnidades;
	private String estadoSede;
	private String email;
	private Double investimentoInicial;
	private String subsegmento;
	private String tipoNegocio;
	private LocalDateTime ultimaAtualizacao;
	private String url;
	private Boolean ativo;
	
	@ManyToOne
    @JoinColumn(name = "segmento_id")
    private Segmento segmento;
	
	
	public void excluir() {
        this.ativo = false;
    }
}