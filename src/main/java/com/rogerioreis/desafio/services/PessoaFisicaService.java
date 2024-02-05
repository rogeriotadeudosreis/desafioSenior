package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.model.Contato;
import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.model.PessoaFisica;
import com.rogerioreis.desafio.model.Telefone;
import com.rogerioreis.desafio.repositories.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TelefoneService telefoneService;

    @Transactional
    public PessoaFisica create(PessoaFisica pessoa) {
        PessoaFisica pessoaSalvar = new PessoaFisica(pessoa);
        pessoaSalvar.setId(null);

        pessoaSalvar = this.pessoaRepository.save(pessoaSalvar);

        Contato contato = pessoaSalvar.getPessoa().getContato();
        Set<Email> emails = pessoaSalvar.getEmails();
        Set<Telefone> telefones = pessoaSalvar.getTelefones();

        emailService.createEmailByContato(contato, emails);
        telefoneService.createTelefoneByContato(contato, telefones);

        return pessoaSalvar;
    }

    public PessoaFisica findById(Long id) {
        return pessoaRepository.findById(id).
                orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa física não encontrada."));
    }

}
