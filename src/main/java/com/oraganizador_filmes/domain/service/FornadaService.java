package com.oraganizador_filmes.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oraganizador_filmes.api.assembler.FornadaAssembler;
import com.oraganizador_filmes.api.dto.model.FornadaModel;
import com.oraganizador_filmes.domain.exception.EntidadeNaoEncontradaException;
import com.oraganizador_filmes.domain.exception.FilmeNaoEncontradoException;
import com.oraganizador_filmes.domain.exception.FornadaNaoEncontradaException;
import com.oraganizador_filmes.domain.exception.NegocioException;
import com.oraganizador_filmes.domain.model.Filme;
import com.oraganizador_filmes.domain.model.Fornada;
import com.oraganizador_filmes.domain.model.FornadaItem;
import com.oraganizador_filmes.domain.repository.FilmeRepository;
import com.oraganizador_filmes.domain.repository.FornadaItemRepository;
import com.oraganizador_filmes.domain.repository.FornadaRepository;

@Service
@Transactional
public class FornadaService {

	@Autowired
	private FornadaRepository fornadaRepository;
	
	@Autowired
	private FornadaItemRepository fornadaItemRepository;
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private FornadaAssembler fornadaAssembler;
	
	public Fornada salvar(Fornada fornada) {
		return fornadaRepository.save(fornada);
	}
	
	public FornadaModel adicionarFilme(Long fornadaId, Long tmdbLink) {
		Fornada fornada = fornadaRepository.findById(fornadaId)
				.orElseThrow(() -> new FornadaNaoEncontradaException("Fornada não encontrada!"));
		
		Filme filme = filmeRepository.findByTmdbLink(tmdbLink)
				.orElseThrow(() -> new FilmeNaoEncontradoException("Filme não encontrado"));
		
		validarFilmeNaoAssistidoUnico(filme, fornada);
		
		FornadaItem item = new FornadaItem();
		item.setFornada(fornada);
        item.setFilme(filme);
        item.setAssistido(false);
        
        fornadaItemRepository.save(item);
        
        Fornada fornadaAtualizada = buscarFornada(fornadaId);
        
        return fornadaAssembler.toModel(fornadaAtualizada);
		
		 
	
	}
	
    public FornadaModel buscarComFilmes(Long fornadaId) {
        Fornada fornada = buscarFornada(fornadaId);
        return fornadaAssembler.toModel(fornada);
    }
    
	public List<FornadaModel> listar() {
		List<Fornada> fornadas = fornadaRepository.findAll();
        return fornadaAssembler.toCollectionModel(fornadas);
	}
	
    public void removerFilme(Long fornadaId, Long tmdbLink) {
        FornadaItem item = fornadaItemRepository.findByFornadaIdAndFilmeTmdbLink(fornadaId, tmdbLink)
            .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme não encontrado na fornada"));
        
        fornadaItemRepository.delete(item);
    }
	
    private Fornada buscarFornada(Long fornadaId) {
        return fornadaRepository.findByIdWithItens(fornadaId)
                .orElseThrow(() -> new RuntimeException("Fornada não encontrada"));
    }
    
    private Filme buscarFilme(Long filmeId) {
        return filmeRepository.findById(filmeId)
            .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
    }
	
	private void validarFilmeNaoAssistidoUnico(Filme filme, Fornada fornadaAtual) {
		List<FornadaItem> itensExistentes = fornadaItemRepository
				.findByFilmeAndAssistidoFalse(filme);
		
		if(!itensExistentes.isEmpty()) {
			FornadaItem itemExistente = itensExistentes.get(0);
			if(!itemExistente.getFornada().getId().equals(fornadaAtual.getId())) {
				throw new NegocioException("Filme '" + filme.getTitulo() + "' já está na fornada '" + 
	                    itemExistente.getFornada().getNome() + "' como não assistido");
			}
		}
	}
}
