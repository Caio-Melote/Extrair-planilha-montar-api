package com.sultsdev.projeto1.model;

import java.time.LocalDateTime;

import com.sultsdev.projeto1.domain.dto.DadosAtualizacaoFranquia;
import com.sultsdev.projeto1.domain.dto.DadosCadastroFranquia;
import com.sultsdev.projeto1.repository.SegmentoRepository;

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
	private Integer totalUnidades;
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

	public void atualizarInformacoes(DadosAtualizacaoFranquia dados, SegmentoRepository segmentoRepository) {
		this.ultimaAtualizacao = LocalDateTime.now();

		if (dados.getNome() != null) {
			this.nome = dados.getNome();
		}
		if (dados.getTotalUnidades() != null) {
			this.totalUnidades = dados.getTotalUnidades();
		}
		if (dados.getEstadoSede() != null) {
			this.estadoSede = dados.getEstadoSede();
		}
		if (dados.getEmail() != null) {
			this.email = dados.getEmail();
		}
		if (dados.getInvestimentoInicial() != null) {
			this.investimentoInicial = dados.getInvestimentoInicial();
		}
		if (dados.getSubsegmento() != null) {
			this.subsegmento = dados.getSubsegmento();
		}
		if (dados.getTipoNegocio() != null) {
			this.tipoNegocio = dados.getTipoNegocio();
		}
		if (dados.getUrl() != null) {
			this.url = dados.getUrl();
		}
		if (dados.getAtivo() != null) {
			this.ativo = dados.getAtivo();
		}
		if (dados.getSegmento() != null) {
			Segmento segmentoExistente = segmentoRepository.findByNome(dados.getSegmento().getNome());
			if (segmentoExistente != null) {
				this.segmento = segmentoExistente;
			} else {
				Segmento novoSegmento = new Segmento(dados.getSegmento());
				segmentoRepository.save(novoSegmento);
				this.segmento = novoSegmento;
			}
		}
	}

	public Franquia(DadosCadastroFranquia dados) {

		this.nome = dados.getNome();
		this.totalUnidades = dados.getTotalUnidades();
		this.estadoSede = dados.getEstadoSede();
		this.email = dados.getEmail();
		this.investimentoInicial = dados.getInvestimentoInicial();
		this.subsegmento = dados.getSubsegmento();
		this.tipoNegocio = dados.getTipoNegocio();
		this.ultimaAtualizacao = LocalDateTime.now();
		this.url = dados.getEmail();

		this.segmento = new Segmento(dados.getSegmento());

		this.ativo = true;
	}

}
