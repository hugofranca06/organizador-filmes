package com.oraganizador_filmes.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oraganizador_filmes.domain.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long>{
	
	boolean existsByTmdbLink(Long tmdbLink);
	Optional<Filme> findByTmdbLink(Long tmdbLink);

}
