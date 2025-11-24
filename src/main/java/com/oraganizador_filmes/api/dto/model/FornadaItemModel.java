package com.oraganizador_filmes.api.dto.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornadaItemModel {

	private Long id;
	private FilmeModel filme;
	private Integer prioridade;
	private Boolean assistido;
	private LocalDate dataVisto;
}
