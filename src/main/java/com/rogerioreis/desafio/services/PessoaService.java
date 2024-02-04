package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.model.Pessoa;
import com.rogerioreis.desafio.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

//    public Pessoa create(Pessoa pessoa) {
//        Pessoa pessoaSalvar = pessoa;
//        pessoaSalvar.setId(null);
//
//        return this.pessoaRepository.save(pessoaSalvar);
//    }
}
