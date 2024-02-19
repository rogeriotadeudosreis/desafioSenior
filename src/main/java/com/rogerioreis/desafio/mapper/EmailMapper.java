package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.EmailResponse;
import com.rogerioreis.desafio.model.Email;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailMapper {

    public EmailResponse ToDTO(Email email) {
        return new EmailResponse(
                email.getId(),
                email.getEmail(),
                email.getTipo());
    }

    public List<EmailResponse> toListDTO(List<Email> emails) {
        List<EmailResponse> emaisResponses = emails.stream()
                .map((email -> this.ToDTO(email)))
                .collect(Collectors.toList());
        return emaisResponses;
    }

}
