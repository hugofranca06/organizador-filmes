package com.oraganizador_filmes.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.oraganizador_filmes.api.assembler.FornadaAssembler;
import com.oraganizador_filmes.api.dto.input.FornadaInput;
import com.oraganizador_filmes.api.dto.input.FornadaItemInput;
import com.oraganizador_filmes.api.dto.model.FornadaModel;
import com.oraganizador_filmes.domain.model.Fornada;
import com.oraganizador_filmes.domain.service.FornadaService;

@RestController
@RequestMapping("/fornada")
public class FornadaController {

	@Autowired
	private FornadaService fornadaService;
	
	@Autowired
	private FornadaAssembler fornadaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FornadaModel adicionar(@RequestBody FornadaInput fornadaInput) {
		Fornada fornada = fornadaAssembler.toEntity(fornadaInput);
		
		return fornadaAssembler.toModel(fornadaService.salvar(fornada));
	}
	
    @GetMapping
    public List<FornadaModel> listarTodas() {
        return fornadaService.listar();
    }
	
	@PostMapping("/{fornadaId}/{tmdbLink}")
	public FornadaModel adicionarFilme(@PathVariable Long fornadaId,
			@PathVariable Long tmdbLink) {
		return fornadaService.adicionarFilme(fornadaId, tmdbLink);
	}
	
    @GetMapping("/{fornadaId}")
    public FornadaModel buscarComFilmes(@PathVariable Long fornadaId) {
        return fornadaService.buscarComFilmes(fornadaId);
    }
    
    @DeleteMapping("/{fornadaId}/filmes/{tmdbLink}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerFilme(
            @PathVariable Long fornadaId, 
            @PathVariable Long tmdbLink) {
        fornadaService.removerFilme(fornadaId, tmdbLink);
    }
    
    @PatchMapping("/filmes/{fornadaItemId}/assistir")
    public FornadaModel marcarAssistirPorFornadaItemId(@PathVariable Long fornadaItemId,
    													@RequestBody FornadaItemInput fornadaItemInput) {
    	return fornadaService.adicionarDataDeAssistido(fornadaItemId, fornadaItemInput.getDataVisto());
    }
    
    @PatchMapping("/filmes/{fornadaItemId}/remover")
    public FornadaModel desmarcarAssistirPorFornadaItemId(@PathVariable Long fornadaItemId) {
    	return fornadaService.retirarDataDeAssistido(fornadaItemId);
    }
   
}
