package com.rogerioreis.desafio.dto;

public record PessoaJuridicaResponse(
        Long id,
        String razaoSocial,
        String nomeFantasia,
        String cnpj
) {
}
