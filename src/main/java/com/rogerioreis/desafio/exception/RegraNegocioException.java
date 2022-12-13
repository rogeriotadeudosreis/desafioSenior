package com.rogerioreis.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RegraNegocioException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public RegraNegocioException(String message) {
        super();
    }
}
