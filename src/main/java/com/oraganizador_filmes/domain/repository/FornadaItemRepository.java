package com.oraganizador_filmes.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oraganizador_filmes.domain.model.Filme;
import com.oraganizador_filmes.domain.model.FornadaItem;

public interface FornadaItemRepository extends JpaRepository<FornadaItem, Long>{
	
	List<FornadaItem> findByFilme(Filme filme);
	List<FornadaItem> findByFilmeAndAssistidoFalse(Filme filme);
	Optional<FornadaItem> findByFornadaIdAndFilmeId(Long fornadaId, Long filmeId);
	
    @Query("SELECT fi FROM FornadaItem fi WHERE fi.fornada.id = :fornadaId")
    List<FornadaItem> findByFornadaId(@Param("fornadaId") Long fornadaId);

}
