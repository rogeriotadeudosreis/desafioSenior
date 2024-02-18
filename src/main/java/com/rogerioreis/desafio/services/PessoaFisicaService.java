package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.PessoaFisicaRequest;
import com.rogerioreis.desafio.dto.PessoaFisicaResponse;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.mapper.PessoaFisicaMapper;
import com.rogerioreis.desafio.model.*;
import com.rogerioreis.desafio.repositories.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private PessoaFisicaMapper pessoaFisicaMapper;

    @Transactional
    public PessoaFisicaResponse create(PessoaFisicaRequest pessoaFisicaRequest) {

        PessoaFisica pessoaFisicaSalvar = pessoaFisicaMapper.toEntity(pessoaFisicaRequest);

        Cliente cliente = pessoaFisicaSalvar.getCliente();
        cliente.setTipoCliente("PF");
        pessoaFisicaSalvar.setCliente(cliente);
        pessoaFisicaSalvar.setId(null);

        pessoaFisicaSalvar = this.pessoaFisicaRepository.save(pessoaFisicaSalvar);

        Contato contato = pessoaFisicaSalvar.getCliente().getContato();
        List<Email> listEmailsSalvar = pessoaFisicaRequest.emails();
        List<Telefone> listTelefonesSalvar = pessoaFisicaRequest.telefones();

        emailService.createEmailByContato(contato, listEmailsSalvar);
        telefoneService.createTelefoneByContato(contato, listTelefonesSalvar);

        PessoaFisicaResponse pessoaFisicaResponse = pessoaFisicaMapper.toDTO(pessoaFisicaSalvar);

        return pessoaFisicaResponse;
    }

    public PessoaFisicaResponse readPessoaFisicaResponseById(Long id) {
        return pessoaFisicaRepository.findById(id).map(pessoaFisicaMapper::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa física com id [" + id + "] não encontrada."));
    }

    public void deletarById(Long id) {
        pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa física com id [" + id + "] não encontrada."));
        pessoaFisicaRepository.deleteById(id);
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

        PessoaFisica pessoaFisica = pessoaFisicaMapper.toEntity(pessoaFisicaRequest);
        pessoaFisica.setId(id);

        PessoaFisica findPessoaFisica = pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa física com id [" + id + "] não encontrada."));

        Cliente cliente = findPessoaFisica.getCliente();
        Contato contato = findPessoaFisica.getCliente().getContato();

        List<Email> emailsRequest = pessoaFisicaRequest.emails();
        List<Telefone> telefonesRequest = pessoaFisicaRequest.telefones();

        emailService.createEmailByContato(contato, emailsRequest);
        telefoneService.createTelefoneByContato(contato, telefonesRequest);

        cliente.setDataAtualizacao(ZonedDateTime.now());
        pessoaFisica.setCliente(cliente);
        pessoaFisicaRepository.save(pessoaFisica);
        System.out.println("final do insert");

    }

    public List<PessoaFisicaResponse> readPessoaFisicaResponseList() {

        List<PessoaFisica> pessoaFisicaList = pessoaFisicaRepository.findAll();

        return pessoaFisicaMapper.toListDTO(pessoaFisicaList);

    }
}
