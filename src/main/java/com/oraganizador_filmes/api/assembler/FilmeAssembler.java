package com.oraganizador_filmes.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oraganizador_filmes.api.dto.input.FilmeInput;
import com.oraganizador_filmes.api.dto.model.FilmeModel;
import com.oraganizador_filmes.domain.model.Filme;

@Component
public class FilmeAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FilmeModel toModel(Filme filme) {
		return modelMapper.map(filme, FilmeModel.class);
	}
	
	public List<FilmeModel> toCollectionModel(List<Filme> filmes) {
		return filmes.stream()
				.map(this::toModel)
				.toList();
	}
	
	public Filme toEntity(FilmeInput filmeInput) {
		return modelMapper.map(filmeInput, Filme.class);
	}
	
	public void copyToEntity(FilmeInput filmeInput, Filme filme) {
		modelMapper.map(filmeInput, filme);
	}
	
}
