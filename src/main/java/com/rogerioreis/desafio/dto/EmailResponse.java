package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumTipoEmail;

public record EmailResponse(
        String email,
        EnumTipoEmail tipo) {


}
