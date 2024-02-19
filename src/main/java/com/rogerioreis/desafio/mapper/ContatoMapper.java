package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.ContatoResponse;
import com.rogerioreis.desafio.dto.EmailResponse;
import com.rogerioreis.desafio.dto.TelefoneResponse;
import com.rogerioreis.desafio.model.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContatoMapper {

    @Autowired
    private EmailMapper emailMapper;

    @Autowired
    private TelefonelMapper telefonelMapper;

    public ContatoResponse toDTO(Contato contato) {

        return new ContatoResponse(
                contato.getId(),
                emailMapper.toListDTO(contato.getEmails()),
                telefonelMapper.toListDTO(contato.getTelefones())
        );
    }
}
