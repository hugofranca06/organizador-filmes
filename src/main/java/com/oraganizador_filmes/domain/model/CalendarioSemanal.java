package com.oraganizador_filmes.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class CalendarioSemanal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate semanaInicio;
	private LocalDate semanaFim;
	
	@ManyToMany
	@JoinTable(
		        name = "calendario_filme",
		        joinColumns = @JoinColumn(name = "calendario_id"),
		        inverseJoinColumns = @JoinColumn(name = "filme_id")
		    )
	private List<Filme> filmes = new ArrayList<>();
	
	@OneToMany(mappedBy = "calendario", cascade = CascadeType.ALL)
    private List<ItemCalendario> itens = new ArrayList<>();
}


