package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumSituacao;

public record ClienteResponse(
        Long id,
        EnumSituacao situacao,
        ContatoResponse contatoResponse,
        PessoaFisicaResponse pessoaFisicaResponse,
        PessoaJuridicaResponse pessoaJuridicaResponse
) {
}
