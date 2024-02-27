package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enun.EnumTipoTelefone;

public record TelefoneResponse(
        Long id,
        String telefone,
        String ddd,
        String ddi,
        EnumTipoTelefone tipoTelefone
) {

}
