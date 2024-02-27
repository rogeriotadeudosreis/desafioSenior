package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enun.EnumSituacao;

public record ClienteResponse(
        Long id,
        EnumSituacao situacao,
        ContatoResponse contatoResponse,
        PessoaFisicaResponse pessoaFisicaResponse,
        PessoaJuridicaResponse pessoaJuridicaResponse
) {
}
