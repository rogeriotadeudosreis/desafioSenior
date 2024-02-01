package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.model.PessoaJuridica;
import com.rogerioreis.desafio.repositories.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaRepository;

    public PessoaJuridica create(PessoaJuridica pessoa) {
        PessoaJuridica pessoaSalvar = pessoa;
        pessoaSalvar.setId(null);

        return this.pessoaRepository.save(pessoaSalvar);
    }
}
