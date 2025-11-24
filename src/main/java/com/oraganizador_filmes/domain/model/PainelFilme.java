package com.oraganizador_filmes.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class PainelFilme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Prioridade prioridade;
	
	@ManyToOne
    @JoinColumn(name = "painel_fornada_id")
    private PainelFornada painelFornada;
	
	@ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;
}
