package com.sultsdev.projeto1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sultsdev.projeto1.domain.dto.DadosAtualizacaoUsuario;
import com.sultsdev.projeto1.domain.dto.DadosCadastroUsuario;
import com.sultsdev.projeto1.domain.dto.DadosRespostaPaginada;
import com.sultsdev.projeto1.domain.dto.DadosUsuarioSemPaginacao;
import com.sultsdev.projeto1.domain.dto.UsuarioListagem;
import com.sultsdev.projeto1.model.Usuario;
import com.sultsdev.projeto1.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity listar(@PageableDefault(size = 20) Pageable paginacao) {
		var page = repository.findAll(paginacao).map(UsuarioListagem::new);
		DadosRespostaPaginada<UsuarioListagem> response = new DadosRespostaPaginada<>(page);
		return ResponseEntity.ok(response);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var usuarioOptional = repository.findById(id);
		if (usuarioOptional.isPresent()) {
			var resposta = new DadosUsuarioSemPaginacao(usuarioOptional.get());

			return ResponseEntity.ok(resposta);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
		var usuario = new Usuario(dados);
		repository.save(usuario);

		var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosUsuarioSemPaginacao(usuario));
	}

	@SuppressWarnings("rawtypes")
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados) {
		var userOptional = repository.findById(dados.getId());
		if (userOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		var usuario = userOptional.get();
		usuario.atualizarInformacoes(dados);

		repository.save(usuario);

		var resposta = new DadosUsuarioSemPaginacao(usuario);

		return ResponseEntity.ok(resposta);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var usuario = repository.getReferenceById(id);
		usuario.excluir();

		return ResponseEntity.noContent().build();
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity reativar(@PathVariable Long id) {
		var usuarioOptional = repository.getReferenceById(id);
		usuarioOptional.reativar();
		
		return ResponseEntity.noContent().build();
	}

	
}
