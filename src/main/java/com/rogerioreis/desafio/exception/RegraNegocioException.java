package com.rogerioreis.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RegraNegocioException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private static final String message = "%s";

    public RegraNegocioException() {
        super();
    }

    public RegraNegocioException(String str) {
        super(String.format(message, str));
    }
}
