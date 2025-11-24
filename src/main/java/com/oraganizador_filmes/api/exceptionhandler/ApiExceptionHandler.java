package com.oraganizador_filmes.api.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.oraganizador_filmes.domain.exception.EntidadeNaoEncontradaException;
import com.oraganizador_filmes.domain.exception.NegocioException;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex) {
		return ex.getMessage();
	}
	
    @ExceptionHandler(NegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNegocio(NegocioException ex) {
        return ex.getMessage();
    }

}
