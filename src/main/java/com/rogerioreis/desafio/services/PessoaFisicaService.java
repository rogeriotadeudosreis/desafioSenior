package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.PessoaFisicaRequest;
import com.rogerioreis.desafio.dto.PessoaFisicaResponse;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.mapper.PessoaFisicaMapper;
import com.rogerioreis.desafio.model.*;
import com.rogerioreis.desafio.repositories.PessoaFisicaRepository;
import com.rogerioreis.desafio.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Set;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private PessoaFisicaMapper mapper;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public PessoaFisicaResponse create(PessoaFisicaRequest pessoaFisicaRequest) {

        PessoaFisica pessoaSalvar = mapper.toEntity(pessoaFisicaRequest);
        pessoaSalvar.setId(null);

        pessoaSalvar = this.pessoaFisicaRepository.save(pessoaSalvar);

        Contato contato = pessoaSalvar.getPessoa().getContato();
        Set<Email> listEmailsSalvar = pessoaFisicaRequest.emails();
        Set<Telefone> listTelefonesSalvar = pessoaFisicaRequest.telefones();

        emailService.createEmailByContato(contato, listEmailsSalvar);
        telefoneService.createTelefoneByContato(contato, listTelefonesSalvar);

        PessoaFisicaResponse pessoaFisicaResponse = mapper.toDTO(pessoaSalvar);

        return pessoaFisicaResponse;
    }

    public PessoaFisicaResponse findById(Long id) {
        return pessoaFisicaRepository.findById(id).map(mapper::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa física não encontrada."));
    }

    public void deletarById(Long id) {
        PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa física não encontrada."));

        Pessoa pessoa = pessoaFisica.getPessoa();
        pessoa.setDataFim(ZonedDateTime.now());

        pessoaRepository.save(pessoa);
    }

    public PessoaFisica findPessoaFisicaById(Long id) {
        PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa física não encontrada."));

        return pessoaFisica;
    }
}
