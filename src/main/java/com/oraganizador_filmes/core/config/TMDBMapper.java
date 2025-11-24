package com.oraganizador_filmes.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oraganizador_filmes.api.dto.input.TMDBFilmeResponse;
import com.oraganizador_filmes.domain.model.Filme;

@Component
public class TMDBMapper {

	@Autowired
	private ModelMapper modelMapper;
	
    public Filme toEntity(TMDBFilmeResponse tmdbResponse) {
        Filme filme = new Filme();
        filme.setTmdbLink(tmdbResponse.getId());
        filme.setTitulo(tmdbResponse.getTitle()); 
        filme.setAnoLancamento(extrairAno(tmdbResponse.getReleaseDate()));
        filme.setDuracaoMinutos(tmdbResponse.getRuntime());
        filme.setPosterUrl(construirUrlImagem(tmdbResponse.getPosterPath()));
        filme.setBackgroundUrl(construirUrlImagem(tmdbResponse.getBackdropPath()));
        filme.setDiretor(tmdbResponse.getDiretor());
        
        System.out.println("Filme mapeado: " + filme);
        return filme;
    }
    
    private Integer extrairAno(String releaseDate) {
        if (releaseDate == null || releaseDate.length() < 4) return null;
        try {
            return Integer.parseInt(releaseDate.substring(0, 4));
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    private String construirUrlImagem(String path) {
        return path != null ? "https://image.tmdb.org/t/p/w500" + path : null;
    }
}
