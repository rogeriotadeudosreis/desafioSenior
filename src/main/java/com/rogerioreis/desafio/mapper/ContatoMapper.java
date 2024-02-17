package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.ContatoResponse;
import com.rogerioreis.desafio.dto.EmailResponse;
import com.rogerioreis.desafio.dto.TelefoneResponse;
import com.rogerioreis.desafio.model.Contato;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContatoMapper {

    public ContatoResponse toDTO(Contato contato) {

        List<EmailResponse> emailResponses = contato.getEmails().stream()
                .map((email) -> new EmailResponse(email.getId(), email.getEmail(), email.getTipo()))
                .collect(Collectors.toList());

        List<TelefoneResponse> telefoneResponses = contato.getTelefones().stream()
                .map((telefone) -> new TelefoneResponse(telefone.getId(), telefone.getTelefone(), telefone.getDdd(), telefone.getDdi(),
                        telefone.getTipoTelefone()))
                .collect(Collectors.toList());

        return new ContatoResponse(
                contato.getId(), emailResponses, telefoneResponses
        );
    }
}
