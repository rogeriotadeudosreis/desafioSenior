package com.rogerioreis.desafio.dto;

public record EnderecoResponse(
        Long id,
        String estado,
        String cidade,
        String cep,
        String bairro,
        String logradouro,
        String numero,
        String complemento,
        String tipoEndereco
) {
}
