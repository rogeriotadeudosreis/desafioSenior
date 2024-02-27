package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enun.EnumTipoEmail;
import com.rogerioreis.desafio.model.Contato;

public record EmailRequest(
        Long id,
        String email,
        EnumTipoEmail tipo,
        Contato contato
) {
}
