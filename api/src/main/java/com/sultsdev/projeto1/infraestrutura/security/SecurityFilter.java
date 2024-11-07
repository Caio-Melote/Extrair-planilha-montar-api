package com.sultsdev.projeto1.infraestrutura.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sultsdev.projeto1.model.Usuario;
import com.sultsdev.projeto1.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		var tokenJWT = recuperarToken(request);

		if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);  
            Usuario usuario = (Usuario) repository.findByLogin(subject);            
            if (usuario != null && usuario.getAtivo()) {               
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {                
                SecurityContextHolder.clearContext();
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Usuário está inativo");
                return;
            }
        }
		 
		 
		filterChain.doFilter(request, response);
	}
	
	private String recuperarToken(HttpServletRequest request) {

		var authorizationHeader = request.getHeader("Authorization");
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return authorizationHeader.replace("Bearer", "").trim();
		}

		return null;
	}
	
}
