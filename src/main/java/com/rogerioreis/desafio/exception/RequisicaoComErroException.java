package com.rogerioreis.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequisicaoComErroException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String message = "%s";

    public RequisicaoComErroException() {
        super();
    }

    public RequisicaoComErroException(String str) {
        super(String.format(message, str));
    }
}
