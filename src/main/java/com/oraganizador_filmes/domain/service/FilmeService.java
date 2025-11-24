package com.oraganizador_filmes.domain.service;

import org.hibernate.boot.beanvalidation.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oraganizador_filmes.api.assembler.FilmeAssembler;
import com.oraganizador_filmes.api.dto.input.TMDBFilmeResponse;
import com.oraganizador_filmes.api.dto.model.FilmeModel;
import com.oraganizador_filmes.core.config.TMDBClient;
import com.oraganizador_filmes.core.config.TMDBMapper;
import com.oraganizador_filmes.domain.exception.NegocioException;
import com.oraganizador_filmes.domain.model.Filme;
import com.oraganizador_filmes.domain.repository.FilmeRepository;

import lombok.AllArgsConstructor;


@Service
@Transactional
@AllArgsConstructor
public class FilmeService {

	@Autowired
    private final FilmeRepository filmeRepository;
	
	@Autowired
    private final TMDBClient tmdbClient;
	
	@Autowired
    private final TMDBMapper tmdbMapper;
	
	@Autowired
    private final FilmeAssembler filmeAssembler;

	
	public FilmeModel criarFilmePorTmdb (Long tmdbId) {
		if(filmeRepository.existsByTmdbLink(tmdbId)) {
			throw new NegocioException("Filme com TMDB ID " + tmdbId + " já existe");
		}
		
		TMDBFilmeResponse tmdbResponse = tmdbClient.buscarFilmePorId(tmdbId)
				.orElseThrow(() -> new IntegrationException("Filme não encontrado no TMDB"));
		
		Filme filme = tmdbMapper.toEntity(tmdbResponse);
		System.out.println(filme.toString());
		
		Filme filmeSalvo = filmeRepository.save(filme);
		System.out.println(filmeSalvo);
		
		return filmeAssembler.toModel(filmeSalvo);

	}

}
