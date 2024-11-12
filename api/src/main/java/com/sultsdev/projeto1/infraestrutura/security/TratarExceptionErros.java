package com.sultsdev.projeto1.infraestrutura.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratarExceptionErros {

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity erro404() {
		return ResponseEntity.notFound().build();
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity erro400(MethodArgumentNotValidException ex) {
		List<FieldError> erros = ex.getFieldErrors();
		List<DadosErroValidacao> dadosErroValidacao = new ArrayList<>();

		for (FieldError erro : erros) {
			dadosErroValidacao.add(new DadosErroValidacao(erro));
		}

		return ResponseEntity.badRequest().body(dadosErroValidacao);
	}

	private record DadosErroValidacao(String campo, String mensagem) {
		public DadosErroValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
		return ResponseEntity.badRequest().body("O ID deve ser informado na URL");
	}
}
