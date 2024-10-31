package com.sultsdev.projeto1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sultsdev.projeto1.domain.Franquia.Franquia;

public interface FranquiaRepository extends JpaRepository<Franquia, Long> {

}
