package com.oraganizador_filmes.api.dto.input;

import java.time.LocalDate;

import com.oraganizador_filmes.domain.model.StatusFornada;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornadaInput {
	
	@NotBlank
	private String nome;	
	private StatusFornada status = StatusFornada.ABERTA;	
	private LocalDate dataCriacao;	
	private LocalDate dataFechamento;

}
