package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumTipoTelefone;
import com.rogerioreis.desafio.model.Contato;

public record TelefoneRequest(
        Long id,
        String telefone,
        String ddd,
        String ddi,
        EnumTipoTelefone tipoTelefone,
        Contato contato
) {

}
