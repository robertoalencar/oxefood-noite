package br.com.ifpe.oxefoodnoite.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends RuntimeException {

    private static final long serialVersionUID = 7075048833457322269L;

    public EntidadeNaoEncontradaException(String entidade, Long id) {
	super(String.format("Não foi encontrado(a) um(a) %s com o id %s", entidade, id.toString()));
    }
}

