package com.oraganizador_filmes.api.exceptionhandler;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProblemType {

	private Integer status;
	private String titulo;
	private OffsetDateTime timestamp;
	
}
