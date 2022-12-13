package com.rogerioreis.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RecursoExistenteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String message = "Recurso com id %s ja existe.";

    public RecursoExistenteException() {
        super();
    }

    public RecursoExistenteException(String str) {
        super(String.format(message, str));
    }
}
