package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.PessoaJuridicaRequest;
import com.rogerioreis.desafio.model.Contato;
import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.model.PessoaJuridica;
import com.rogerioreis.desafio.model.Telefone;
import com.rogerioreis.desafio.repositories.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TelefoneService telefoneService;

    public PessoaJuridica create(PessoaJuridicaRequest pessoaJuridicaRequest) {
        PessoaJuridica pessoaJuridicaSalvar = new PessoaJuridica(pessoaJuridicaRequest);
        pessoaJuridicaSalvar.setId(null);

        Set<Email> emails = pessoaJuridicaRequest.emails();
        Set<Telefone> telefones = pessoaJuridicaRequest.telefones();

        pessoaJuridicaSalvar = this.pessoaRepository.save(pessoaJuridicaSalvar);

        Contato contato = pessoaJuridicaRequest.pessoa().getContato();
        emailService.createEmailByContato(contato, emails);
        telefoneService.createTelefoneByContato(contato, telefones);

        return pessoaJuridicaSalvar;
    }

}
