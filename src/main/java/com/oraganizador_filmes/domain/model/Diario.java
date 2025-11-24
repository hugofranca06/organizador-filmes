package com.oraganizador_filmes.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Diario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate dataVisualizacao;
	
	private Double nota;
	
	private Boolean revisto;
	
	private Integer numeroNoMes;
	
	private Integer numeroNoAno;
	
	@ManyToOne
    @JoinColumn(name = "filme_id")
	private Filme filme;
	
	
}
