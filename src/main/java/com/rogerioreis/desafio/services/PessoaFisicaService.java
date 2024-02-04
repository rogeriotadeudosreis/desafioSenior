package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.model.PessoaFisica;
import com.rogerioreis.desafio.repositories.PessoaFisicaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaRepository;

    @Autowired
    private EmailService emailService;

    public PessoaFisica create(PessoaFisica pessoa) {
        PessoaFisica pessoaSalvar = new PessoaFisica(pessoa);
        pessoaSalvar.setId(null);

        pessoaSalvar = this.pessoaRepository.save(pessoaSalvar);

        Set<Email> emails = pessoaSalvar.getContato().getEmails();

        if (emails != null && !emails.isEmpty()) {
            for (Email email : emails) {
                if (StringUtils.isNotBlank(email.getEmail())) {
                    Email emailSalvar = new Email(null, email.getEmail(), email.getTipo(),
                            pessoaSalvar.getPessoa().getContato());
                    emailService.create(emailSalvar);
                } else {
                    throw new RegraNegocioException("É necessário informar pelo menos 01(um) email válido.");
                }
            }
        } else {
            throw new RegraNegocioException("A lista de emails está nula ou vazia.");
        }
        return pessoaSalvar;
    }

    public PessoaFisica findById(Long id) {
        return pessoaRepository.findById(id).
                orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa física não encontrada."));
    }
}
