package com.oraganizador_filmes.api.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeModel {
	
	private Long id;
    private String titulo;
    private Integer anoLancamento;
    private String diretor;
    private Integer duracaoMinutos;
    private String posterUrl;
    private String backgroundUrl;
    private Long tmdbLink;

}
