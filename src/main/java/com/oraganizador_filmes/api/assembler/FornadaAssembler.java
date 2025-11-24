package com.oraganizador_filmes.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oraganizador_filmes.api.dto.input.FornadaInput;
import com.oraganizador_filmes.api.dto.model.FornadaItemModel;
import com.oraganizador_filmes.api.dto.model.FornadaModel;
import com.oraganizador_filmes.domain.model.Fornada;
import com.oraganizador_filmes.domain.model.FornadaItem;

@Component
public class FornadaAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FilmeAssembler filmeAssembler;
	
	public FornadaModel toModel(Fornada fornada) {
		FornadaModel fornadaModel = modelMapper.map(fornada, FornadaModel.class);
		
		if(fornada.getItens() != null) {
			List<FornadaItemModel> itensModel = fornada.getItens().stream()
					.map(this::toItemModel)
					.toList();
			fornadaModel.setItens(itensModel);
		}
		
		return fornadaModel;
	}
	
	public FornadaItemModel toItemModel(FornadaItem item) {
        FornadaItemModel itemModel = modelMapper.map(item, FornadaItemModel.class);
        itemModel.setFilme(filmeAssembler.toModel(item.getFilme()));
        return itemModel;
    }
	
	public List<FornadaModel> toCollectionModel(List<Fornada> fornadas) {
		return fornadas.stream()
				.map(this::toModel)
				.toList();
	}
	
	public Fornada toEntity(FornadaInput fornadaInput) {
		return modelMapper.map(fornadaInput, Fornada.class);
	}

}
