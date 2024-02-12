package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.ContatoResponse;
import com.rogerioreis.desafio.dto.EmailResponse;
import com.rogerioreis.desafio.dto.TelefoneResponse;
import com.rogerioreis.desafio.model.Contato;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ContatoMapper {

    public ContatoResponse toDTO(Contato contato) {

        Set<EmailResponse> emailResponses = contato.getEmails().stream()
                .map((email) -> new EmailResponse(email.getEmail(), email.getTipo()))
                .collect(Collectors.toSet());

        Set<TelefoneResponse> telefoneResponses = contato.getTelefones().stream()
                .map((telefone) -> new TelefoneResponse(telefone.getTelefone(), telefone.getDdd(), telefone.getDdi(),
                        telefone.getTipoTelefone()))
                .collect(Collectors.toSet());

        return new ContatoResponse(
                emailResponses, telefoneResponses
        );
    }
}
