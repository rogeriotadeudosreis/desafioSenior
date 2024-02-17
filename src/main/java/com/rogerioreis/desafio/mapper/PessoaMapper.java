package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.PessoaResponse;
import com.rogerioreis.desafio.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PessoaMapper {

    @Autowired
    private PessoaFisicaMapper pessoaFisicaMapper;

    @Autowired
    private ContatoMapper contatoMapper;

    public PessoaResponse toDTO(Pessoa pessoa) {
        return new PessoaResponse(
                pessoa.getId(),
                pessoa.getSituacao(),
                contatoMapper.toDTO(pessoa.getContato()),
                pessoaFisicaMapper.toDTO(pessoa.getPessoaFisica())
        );
    }

    public List<PessoaResponse> toListDTO(List<Pessoa> pessoaList) {
        List<PessoaResponse> pessoaResponseList = pessoaList.stream()
                .map((pessoa ->
                        new PessoaResponse(
                                pessoa.getId(), pessoa.getSituacao(),
                                contatoMapper.toDTO(pessoa.getContato()),
                                pessoaFisicaMapper.toDTO(pessoa.getPessoaFisica())
                        )
                )).collect(Collectors.toList());
        return pessoaResponseList;
    }


}
