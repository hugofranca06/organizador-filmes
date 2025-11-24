package com.oraganizador_filmes.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oraganizador_filmes.domain.model.Fornada;

public interface FornadaRepository extends JpaRepository<Fornada, Long>{
	
    @Query("SELECT f FROM Fornada f LEFT JOIN FETCH f.itens WHERE f.id = :id")
    Optional<Fornada> findByIdWithItens(@Param("id") Long id);
    
    @Query("SELECT DISTINCT f FROM Fornada f LEFT JOIN FETCH f.itens")
    List<Fornada> findAllWithItens();

}
