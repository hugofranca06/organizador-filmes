package com.oraganizador_filmes.api.dto.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.oraganizador_filmes.domain.model.FornadaItem;
import com.oraganizador_filmes.domain.model.StatusFornada;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornadaModel {
	
	private Long id;
	private String nome;	
	private StatusFornada status = StatusFornada.ABERTA;	
	private LocalDate dataCriacao;	
	private LocalDate dataFechamento;
	private List<FornadaItemModel> itens;

}
