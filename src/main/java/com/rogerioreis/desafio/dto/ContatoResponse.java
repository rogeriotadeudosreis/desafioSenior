package com.rogerioreis.desafio.dto;

import java.util.List;

public record ContatoResponse(
        Long id,
        List<EmailResponse> emailsResponse,
        List<TelefoneResponse> telefonesResponse,
        List<EnderecoResponse> enderecoResponses) {
}
