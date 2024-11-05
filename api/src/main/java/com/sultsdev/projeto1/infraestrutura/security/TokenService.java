package com.sultsdev.projeto1.infraestrutura.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sultsdev.projeto1.model.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String geraToken(Usuario user) {
		try {
			var algorithm = Algorithm.HMAC256(secret);

			String token = JWT.create().withIssuer("API Planilha Franquias").withSubject(user.getLogin())
					.withClaim("id", user.getId()).withExpiresAt(dataExpiracao()).sign(algorithm);
			return token;

		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao tentar gerar o token: " + exception);
		}
	}
	
	public String getSubject(String tokenJWT) {
	    try {
	        var algoritmo = Algorithm.HMAC256(secret);
	        return JWT.require(algoritmo)
	                        .withIssuer("API Planilha Franquias")
	                        .build()
	                        .verify(tokenJWT)
	                        .getSubject();
	    } catch (JWTVerificationException exception) {
	        throw new RuntimeException("Token JWT inválido ou expirado!");
	    }
	}
	
	private Instant dataExpiracao() {

		return LocalDateTime.now().plusHours(25).toInstant(ZoneOffset.of("-03:00"));
	}
	
}
