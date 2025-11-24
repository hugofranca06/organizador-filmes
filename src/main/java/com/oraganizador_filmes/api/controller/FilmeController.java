package com.oraganizador_filmes.api.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oraganizador_filmes.api.assembler.FilmeAssembler;
import com.oraganizador_filmes.api.dto.model.FilmeModel;
import com.oraganizador_filmes.domain.repository.FilmeRepository;
import com.oraganizador_filmes.domain.service.FilmeService;

@RestController
@RequestMapping("filmes")
public class FilmeController {

	@Autowired
	private FilmeService filmeService;
	
	@Autowired
	private FilmeAssembler filmeAssembler;
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public FilmeModel adicionarPorTmdb(@RequestBody String tmdbLink) {
		Long tmdbId = extractMovieId(tmdbLink);
		System.out.println("teste branch");
		return filmeService.criarFilmePorTmdb(tmdbId);
		
	}
	
	@GetMapping()
	public List<FilmeModel> listarFilmes() {
		return filmeAssembler.toCollectionModel(filmeRepository.findAll());
	}
	
	private Long extractMovieId(String url) {
		var pattern = Pattern.compile("movie/(\\d+)");
		var matcher = pattern.matcher(url);
		
		if(matcher.find()) {
			return Long.parseLong(matcher.group(1));
		}
		throw new IllegalArgumentException("ID não encontrado na url " + url);
	}
	
	
}
