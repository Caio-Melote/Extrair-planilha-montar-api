package com.sultsdev.projeto1.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.sultsdev.projeto1.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{

	UserDetails findByLogin(String login);
	
	Page<Usuario> findAllByAtivoTrue(Pageable paginacao);
	
	Optional<Usuario> findByIdAndAtivoTrue(Long id);
}
