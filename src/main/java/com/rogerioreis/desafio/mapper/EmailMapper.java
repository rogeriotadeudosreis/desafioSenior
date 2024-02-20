package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.EmailRequest;
import com.rogerioreis.desafio.dto.EmailResponse;
import com.rogerioreis.desafio.model.Email;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailMapper {

    public EmailResponse toDTO(Email email) {
        return new EmailResponse(
                email.getId(),
                email.getEmail(),
                email.getTipo());
    }

    public List<EmailResponse> toListDTO(List<Email> emails) {
        List<EmailResponse> emaisResponses = emails.stream()
                .map((email -> this.toDTO(email)))
                .collect(Collectors.toList());
        return emaisResponses;
    }

    public Email toEntity(EmailRequest emailRequest) {
        return new Email(
                emailRequest.id(),
                emailRequest.email(),
                emailRequest.tipo(),
                null, null, null,
                emailRequest.contato()
        );
    }

}
