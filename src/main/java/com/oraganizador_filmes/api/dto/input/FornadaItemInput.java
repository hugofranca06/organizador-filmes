package com.oraganizador_filmes.api.dto.input;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornadaItemInput {
	private Long filmeId;
	private Integer prioridade;
	private LocalDate dataVisto;

}
