package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumSituacao;

import java.time.ZonedDateTime;

public record PessoaResponse(
        ZonedDateTime dataCadastro,
        EnumSituacao situacao) {
}
