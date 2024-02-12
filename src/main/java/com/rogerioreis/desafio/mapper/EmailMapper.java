package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.EmailResponse;
import com.rogerioreis.desafio.model.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    public EmailResponse ToDTO(Email email) {
        return new EmailResponse(
                email.getEmail(),
                email.getTipo());

    }

}
