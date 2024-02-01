package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.model.PessoaFisica;
import com.rogerioreis.desafio.repositories.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaRepository;

    public PessoaFisica create(PessoaFisica pessoa) {
        PessoaFisica pessoaSalvar = pessoa;
        pessoaSalvar.setId(null);

        return this.pessoaRepository.save(pessoaSalvar);
    }
}
