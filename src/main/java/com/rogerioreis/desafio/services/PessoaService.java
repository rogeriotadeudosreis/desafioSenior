package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.model.Pessoa;
import com.rogerioreis.desafio.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void update(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            throw new RecursoNaoEncontradoException("É necessário informar o [id] da entidade [Pessoa]" +
                    "para atualizar");
        }
        Pessoa pessoaUpdate = pessoa;
        pessoaUpdate.setId(pessoa.getId());
        pessoaRepository.save(pessoaUpdate);
    }

}
