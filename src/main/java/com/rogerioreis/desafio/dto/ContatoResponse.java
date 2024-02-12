package com.rogerioreis.desafio.dto;

import java.util.Set;

public record ContatoResponse(
        Set<EmailResponse> emailsResponse,
        Set<TelefoneResponse> telefonesResponse) {
}
