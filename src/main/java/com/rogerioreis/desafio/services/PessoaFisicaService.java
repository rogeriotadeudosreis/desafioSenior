package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.PessoaFisicaRequest;
import com.rogerioreis.desafio.dto.PessoaFisicaResponse;
import com.rogerioreis.desafio.enuns.EnumTipoEmail;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.mapper.PessoaFisicaMapper;
import com.rogerioreis.desafio.model.*;
import com.rogerioreis.desafio.repositories.PessoaFisicaRepository;
import com.rogerioreis.desafio.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
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

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ContatoService contatoService;

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

    public PessoaFisicaResponse readPessoaFisicaResponseById(Long id) {
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

    public PessoaFisica readPessoaFisicaEntityById(Long id) {
        PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa física com id [" + id + "] não encontrada."));
        return pessoaFisica;
    }

    public void update(Long id, PessoaFisicaRequest pessoaFisicaRequest) {

        if (id == null || pessoaFisicaRequest == null) {
            throw new RegraNegocioException("É necessário informar o id e dados da pessoa física para atualizar.");
        }

        PessoaFisica pessoaFisica = mapper.toEntity(pessoaFisicaRequest);
        pessoaFisica.setId(id);

        PessoaFisica findPessoaFisica = this.readPessoaFisicaEntityById(pessoaFisica.getId());

        Long idPessoa = findPessoaFisica.getPessoa().getId();
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa não encontrada."));

        Contato contato = pessoa.getContato();

        Set<Email> emailsRequest = pessoaFisicaRequest.emails();
        Set<Email> emails = emailService.findEmailsByContato(contato.getId());

        

        Set<Telefone> telefones = telefoneService.findTelefonesByContato(contato.getId());

        pessoa.setDataAtualizacao(ZonedDateTime.now());
        pessoaFisica.setPessoa(pessoa);
        pessoaFisicaRepository.save(pessoaFisica);

    }
}
