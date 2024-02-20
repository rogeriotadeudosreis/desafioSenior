package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.ContatoResponse;
import com.rogerioreis.desafio.model.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapper {

    @Autowired
    private EmailMapper emailMapper;

    @Autowired
    private TelefonelMapper telefonelMapper;

    @Autowired
    private EnderecoMapper enderecoMapper;

    public ContatoResponse toDTO(Contato contato) {

        return new ContatoResponse(
                contato.getId(),
                emailMapper.toListDTO(contato.getEmails()),
                telefonelMapper.toListDTO(contato.getTelefones()),
                enderecoMapper.toListDTO(contato.getEnderecos())
        );
    }
}
