package com.rogerioreis.desafio.dto;

import java.util.List;
import java.util.Set;

public record ContatoResponse(
        Long id,
        List<EmailResponse> emailsResponse,
        List<TelefoneResponse> telefonesResponse) {
}
