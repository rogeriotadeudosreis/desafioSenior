package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.PessoaJuridicaRequest;
import com.rogerioreis.desafio.dto.PessoaJuridicaResponse;
import com.rogerioreis.desafio.enuns.EnumTipoCliente;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.mapper.PessoaJuridicaMapper;
import com.rogerioreis.desafio.model.*;
import com.rogerioreis.desafio.repositories.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository juridicaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaJuridicaMapper pessoaJuridicaMapper;

    @Transactional
    public PessoaJuridicaResponse create(PessoaJuridicaRequest pessoaJuridicaRequest) {
        PessoaJuridica pessoaJuridicaSalvar = pessoaJuridicaMapper.toEntity(pessoaJuridicaRequest);

        Cliente clienteSalvar = pessoaJuridicaSalvar.getCliente();
        clienteSalvar.setTipoCliente(EnumTipoCliente.PJ);
        pessoaJuridicaSalvar.setCliente(clienteSalvar);
        pessoaJuridicaSalvar.setId(null);

        pessoaJuridicaSalvar = this.juridicaRepository.save(pessoaJuridicaSalvar);

        Cliente clienteRetorno = pessoaJuridicaSalvar.getCliente();
        Contato contato = clienteRetorno.getContato();
        List<Email> emails = pessoaJuridicaRequest.emails();
        List<Telefone> telefones = pessoaJuridicaRequest.telefones();
        List<Endereco> enderecos = pessoaJuridicaRequest.enderecos();

        emailService.createEmailByContato(contato, emails);
        telefoneService.createTelefoneByContato(contato, telefones);
        enderecoService.createEnderecoByContato(contato, enderecos);

        PessoaJuridicaResponse pessoaJuridicaResponse = pessoaJuridicaMapper.toDTO(pessoaJuridicaSalvar);

        return pessoaJuridicaResponse;
    }

    @Transactional
    public void update(Long id, PessoaJuridicaRequest pessoaJuridicaRequest) {

        if (id == null || pessoaJuridicaRequest == null) {
            throw new RegraNegocioException("É necessário informa o ID e dados de pessoa jurídica para atualizar.");
        }

        PessoaJuridica pessoaJuridicaFind = juridicaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de pessoa jurídica não encontrada"));

        PessoaJuridica pessoaJuridicaAtualizar = pessoaJuridicaMapper.toEntity(pessoaJuridicaRequest);
        pessoaJuridicaAtualizar.setId(pessoaJuridicaFind.getId());

        Cliente clienteFind = pessoaJuridicaFind.getCliente();
        Contato contatoFind = clienteFind.getContato();

        List<Email> emailsRequest = pessoaJuridicaRequest.emails();
        List<Telefone> telefonesRequest = pessoaJuridicaRequest.telefones();
        List<Endereco> enderecosRequest = pessoaJuridicaRequest.enderecos();

        emailService.createEmailByContato(contatoFind, emailsRequest);
        telefoneService.createTelefoneByContato(contatoFind, telefonesRequest);
        enderecoService.createEnderecoByContato(contatoFind, enderecosRequest);

        clienteFind.setDataAtualizacao(ZonedDateTime.now());
        pessoaJuridicaAtualizar.setCliente(clienteFind);
        juridicaRepository.save(pessoaJuridicaAtualizar);
    }

    @Transactional
    public PessoaJuridicaResponse readPessoaJuridicaResponseById(Long id) {
        return juridicaRepository.findById(id)
                .map(pessoaJuridicaMapper::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de pessoa jurídica com o [" + id + "] não encontrado"));
    }

    @Transactional
    public PessoaJuridica readPessoaJuridicaById(Long id) {
        return juridicaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Registro de " +
                "pessoa jurídica para o id [" + id + "] não encontrado'"));
    }

    @Transactional
    public void deleteById(Long id){
        this.readPessoaJuridicaById(id);
        juridicaRepository.deleteById(id);
    }


}
