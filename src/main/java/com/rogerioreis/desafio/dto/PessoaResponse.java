package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumSituacao;

import java.time.ZonedDateTime;

public record PessoaResponse(
        ZonedDateTime dataInicio,
        EnumSituacao situacao,
        ContatoResponse contatoResponse) {
}
