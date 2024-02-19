package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.PessoaFisicaRequest;
import com.rogerioreis.desafio.dto.PessoaFisicaResponse;
import com.rogerioreis.desafio.enuns.EnumTipoCliente;
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
    private EnderecoService enderecoService;

    @Autowired
    private PessoaFisicaMapper pessoaFisicaMapper;

    @Transactional
    public PessoaFisicaResponse create(PessoaFisicaRequest pessoaFisicaRequest) {

        PessoaFisica pessoaFisicaSalvar = pessoaFisicaMapper.toEntity(pessoaFisicaRequest);

        Cliente clienteSalvar = pessoaFisicaSalvar.getCliente();
        clienteSalvar.setTipoCliente(EnumTipoCliente.PF);
        pessoaFisicaSalvar.setCliente(clienteSalvar);
        pessoaFisicaSalvar.setId(null);

        pessoaFisicaSalvar = this.pessoaFisicaRepository.save(pessoaFisicaSalvar);

        Cliente clienteRetorno = pessoaFisicaSalvar.getCliente();
        Contato contato = pessoaFisicaSalvar.getCliente().getContato();
        List<Email> listEmailsSalvar = pessoaFisicaRequest.emails();
        List<Telefone> listTelefonesSalvar = pessoaFisicaRequest.telefones();
        List<Endereco> listEnderecosalvar = pessoaFisicaRequest.enderecos();

        emailService.createEmailByContato(contato, listEmailsSalvar);
        telefoneService.createTelefoneByContato(contato, listTelefonesSalvar);
        enderecoService.createEnderecoByCliente(clienteRetorno,listEnderecosalvar);

        PessoaFisicaResponse pessoaFisicaResponse = pessoaFisicaMapper.toDTO(pessoaFisicaSalvar);

        return pessoaFisicaResponse;
    }

    @Transactional
    public void update(Long id, PessoaFisicaRequest pessoaFisicaRequest) {

        if (id == null || pessoaFisicaRequest == null) {
            throw new RegraNegocioException("É necessário informar o id e dados da pessoa física para atualizar.");
        }

        PessoaFisica pessoaFisicaFind = pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa física com id [" + id + "] não encontrada."));

        PessoaFisica pessoaFisicaAtualizar = pessoaFisicaMapper.toEntity(pessoaFisicaRequest);
        pessoaFisicaAtualizar.setId(pessoaFisicaFind.getId());

        Cliente clientFind = pessoaFisicaFind.getCliente();
        Contato contato = clientFind.getContato();

        List<Email> emailsRequest = pessoaFisicaRequest.emails();
        List<Telefone> telefonesRequest = pessoaFisicaRequest.telefones();
        List<Endereco> enderecosRequest = pessoaFisicaRequest.enderecos();

        emailService.createEmailByContato(contato, emailsRequest);
        telefoneService.createTelefoneByContato(contato, telefonesRequest);
        enderecoService.createEnderecoByCliente(clientFind,enderecosRequest);

        clientFind.setDataAtualizacao(ZonedDateTime.now());
        pessoaFisicaAtualizar.setCliente(clientFind);
        pessoaFisicaRepository.save(pessoaFisicaAtualizar);

    }

    @Transactional
    public PessoaFisicaResponse readPessoaFisicaResponseById(Long id) {
        return pessoaFisicaRepository.findById(id).map(pessoaFisicaMapper::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa física com id [" + id + "] não encontrada."));
    }

    @Transactional
    public PessoaFisica readPessoaFisicaEntityById(Long id) {
        PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa física com id [" + id + "] não encontrada."));
        return pessoaFisica;
    }

    @Transactional
    public void deletarById(Long id) {
       this.readPessoaFisicaEntityById(id);
        pessoaFisicaRepository.deleteById(id);
    }

    @Transactional
    public List<PessoaFisicaResponse> readPessoaFisicaResponseList() {

        List<PessoaFisica> pessoaFisicaList = pessoaFisicaRepository.findAll();

        return pessoaFisicaMapper.toListDTO(pessoaFisicaList);

    }
}
