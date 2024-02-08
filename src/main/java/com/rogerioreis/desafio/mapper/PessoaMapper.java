package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.PessoaResponse;
import com.rogerioreis.desafio.enuns.EnumSituacao;
import com.rogerioreis.desafio.model.Pessoa;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class PessoaMapper {

    public PessoaResponse ToDTO(Pessoa pessoa) {
        return new PessoaResponse(
                pessoa.getDataCadastro(),
                pessoa.getSituacao()
        );
    }


}
