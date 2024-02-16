package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumSituacao;

public record PessoaResponse(
        Long id,
        EnumSituacao situacao,
        ContatoResponse contatoResponse) {
}
