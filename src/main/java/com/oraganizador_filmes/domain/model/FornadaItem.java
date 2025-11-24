package com.oraganizador_filmes.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"fornada_id", "filme_id"})
	})
public class FornadaItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "fornada_id")
	private Fornada fornada;
	
	@ManyToOne
	@JoinColumn(name = "filme_id")
	private Filme filme;
	
	private Integer priodidade;
	
	private Boolean assistido;

	private LocalDate dataVisto;
}
