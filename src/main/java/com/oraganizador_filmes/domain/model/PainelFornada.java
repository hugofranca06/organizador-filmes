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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class PainelFornada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private StatusPainelFornada statusPainelFornada;
	
	private LocalDate dataAbertura;
	
	private LocalDate dataFechamento;
	
	private Integer quantidadeSelecionada;
	
	@ManyToOne
    @JoinColumn(name = "planejamento_fornada_id")
    private PlanejamentoFornada planejamentoFornada;
	
	@OneToMany(mappedBy = "painelFornada", cascade = CascadeType.ALL)
    private List<PainelFilme> filmes = new ArrayList<>();
	
}
