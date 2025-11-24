package com.oraganizador_filmes.domain.model;

import java.util.ArrayList;
import java.util.List;

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
public class PlanejamentoFornada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private Integer quantidadePlanejada;	
	private Integer prioridadeP1;	
	private Integer prioridadeP2;	
	private Integer prioridadeP3;
	private Integer quantidadeVistas;
	
	@ManyToOne
	@JoinColumn(name = "fornada_id")
	private Fornada fornada;
	
	@ManyToOne
	@JoinColumn(name = "planejamentoAnual_id")
	private PlanejamentoAnual planejamentoAnual;
	
	@OneToMany(mappedBy = "planejamentoFornada")
    private List<PainelFornada> paineis = new ArrayList<>();
	
}
