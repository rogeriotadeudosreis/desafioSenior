package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumTipoEmail;
import com.rogerioreis.desafio.model.Contato;

public record EmailRequest(
        Long id,
        String email,
        EnumTipoEmail tipo,
        Contato contato
) {
}
