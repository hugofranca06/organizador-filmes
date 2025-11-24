package com.oraganizador_filmes.api.dto.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeInput {
	
	@NotBlank
	private String titulo;
	
	@Min(1888)
    private Integer anoLancamento;
	
    private String diretor;
    
    @Positive
    private Integer duracaoMinutos;
    private String posterUrl;
    private String backgroundUrl;
    private Long tmdbLink;

}
