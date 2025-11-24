package com.oraganizador_filmes.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Fornada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	
	@Enumerated(EnumType.STRING)
	private StatusFornada status = StatusFornada.FECHADA;
	
	private LocalDate dataCriacao = LocalDate.now();
	
	private LocalDate dataFechamento;
	
	@OneToMany(mappedBy = "fornada", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FornadaItem> itens = new ArrayList<>();
	
	//@OneToMany(mappedBy = "fornada")
	//private List<PlanejamentoFornada> planejamentos = new ArrayList<>();
	
}
