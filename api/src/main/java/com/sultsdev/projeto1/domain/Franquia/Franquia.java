package com.sultsdev.projeto1.domain.Franquia;

import java.time.LocalDateTime;

import com.sultsdev.projeto1.domain.segmento.Segmento;

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
import lombok.Setter;

@Table(name = "franquias")
@Entity(name = "Franquia")
@Getter
@Setter
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
    @JoinColumn(name = "segmentos")
    private Segmento segmento;
	
	
	public void excluir() {
        this.ativo = false;
    }
}
