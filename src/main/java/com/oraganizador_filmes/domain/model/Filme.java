package com.oraganizador_filmes.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Filme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private Integer anoLancamento;
	
	private String diretor;
	
	private Integer duracaoMinutos;
	
	private String posterUrl;
	
	private String backgroundUrl;
	@Column(nullable = false, unique = true)
	private Long tmdbLink;
	
	@OneToMany(mappedBy = "filme")
	private List<FornadaItem> fornadaItens = new ArrayList<>();
	
	//@OneToMany(mappedBy = "filme")
	//private List<Diario> registrosDiario = new ArrayList<>();
	
	//@OneToMany(mappedBy = "filme")
	//private List<PainelFilme> paneis = new ArrayList<>();
	
	//@ManyToMany(mappedBy = "filmes")
	//private List<CalendarioSemanal> calendarios = new ArrayList<>();
}
