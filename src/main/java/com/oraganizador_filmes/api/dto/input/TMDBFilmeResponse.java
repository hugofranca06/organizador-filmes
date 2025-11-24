package com.oraganizador_filmes.api.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TMDBFilmeResponse {
	
	@JsonProperty("id")
    private Long id;
    
    @JsonProperty("title")
    private String title;
    
    @JsonProperty("release_date")
    private String releaseDate;
    
    @JsonProperty("runtime")
    private Integer runtime;
    
    @JsonProperty("poster_path")
    private String posterPath;
    
    @JsonProperty("backdrop_path")
    private String backdropPath;
    
    // Campo customizado (não vem da API)
    private String diretor;

}
