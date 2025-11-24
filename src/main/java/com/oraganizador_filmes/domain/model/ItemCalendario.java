package com.oraganizador_filmes.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ItemCalendario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String linkAssistir;
    
    @ManyToOne
    @JoinColumn(name = "calendario_id")
    private CalendarioSemanal calendario;
    
    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;

}
