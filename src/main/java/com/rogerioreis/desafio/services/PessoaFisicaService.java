package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.PessoaFisicaRequest;
import com.rogerioreis.desafio.dto.PessoaFisicaResponse;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.mapper.PessoaFisicaMapper;
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

    @Autowired
    private PessoaFisicaMapper mapper;

    @Transactional
    public PessoaFisicaResponse create(PessoaFisicaRequest pessoaFisicaRequest) {

        PessoaFisica pessoaSalvar = mapper.toEntity(pessoaFisicaRequest);
        pessoaSalvar.setId(null);

        pessoaSalvar = this.pessoaRepository.save(pessoaSalvar);

        Contato contato = pessoaSalvar.getPessoa().getContato();
        Set<Email> emails = pessoaFisicaRequest.emails();
        Set<Telefone> telefones = pessoaFisicaRequest.telefones();

        Set<Email> emailsSalvos = emailService.createEmailByContato(contato, emails);
        Set<Telefone> telefonesSalvos = telefoneService.createTelefoneByContato(contato, telefones);

        PessoaFisicaResponse pessoaFisicaResponse = mapper.toDTO(pessoaSalvar, emailsSalvos, telefonesSalvos);

        return pessoaFisicaResponse;
    }

    public PessoaFisicaResponse findById(Long id) {
        return pessoaRepository.findById(id).map((PessoaFisica pessoaFisica) -> mapper.toDTO(pessoaFisica, null, null))
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa física não encontrada."));
    }

}
