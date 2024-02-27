package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enun.EnumTipoEndereco;
import com.rogerioreis.desafio.model.Contato;

public record EnderecoRequest(
        Long id,
        String estado,
        String localidade,
        String cep,
        String bairro,
        String logradouro,
        String numero,
        String complemento,
        EnumTipoEndereco tipoEndereco,
        Contato contato) {
}
