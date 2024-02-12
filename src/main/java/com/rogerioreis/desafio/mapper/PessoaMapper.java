package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.PessoaResponse;
import com.rogerioreis.desafio.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

    @Autowired
    private ContatoMapper contatoMapper;

    public PessoaResponse ToDTO(Pessoa pessoa) {
        return new PessoaResponse(
                pessoa.getDataInicio(),
                pessoa.getSituacao(),
                contatoMapper.toDTO(pessoa.getContato())
        );
    }


}
