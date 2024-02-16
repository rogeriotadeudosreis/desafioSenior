package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumTipoEmail;

public record EmailResponse(
        Long id,
        String email,
        EnumTipoEmail tipo) {


}
