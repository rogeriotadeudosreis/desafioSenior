package com.rogerioreis.desafio.services;

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

    public PessoaJuridica create(PessoaJuridica pessoaJuridica) {
        PessoaJuridica pessoaJuridicaSalvar = new PessoaJuridica(pessoaJuridica);
        pessoaJuridicaSalvar.setId(null);

        Set<Email> emails = pessoaJuridica.getEmails();
        Set<Telefone> telefones = pessoaJuridica.getTelefones();

        pessoaJuridicaSalvar = this.pessoaRepository.save(pessoaJuridicaSalvar);

        Contato contato = pessoaJuridica.getPessoa().getContato();
        emailService.createEmailByContato(contato, emails);
        telefoneService.createTelefoneByContato(contato, telefones);

        return pessoaJuridicaSalvar;
    }

}
