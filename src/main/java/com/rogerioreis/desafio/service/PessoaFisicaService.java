package com.rogerioreis.desafio.service;

import com.rogerioreis.desafio.dto.PessoaFisicaRequest;
import com.rogerioreis.desafio.dto.PessoaFisicaResponse;
import com.rogerioreis.desafio.enun.EnumTipoCliente;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.mapper.PessoaFisicaMapper;
import com.rogerioreis.desafio.model.*;
import com.rogerioreis.desafio.projection.PessoaFisicaProjection;
import com.rogerioreis.desafio.repository.PessoaFisicaRepository;
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

    @Autowired
    private ClienteService clienteService;

    @Transactional
    public PessoaFisicaResponse create(PessoaFisicaRequest pessoaFisicaRequest) {

        PessoaFisica pessoaFisicaSalvar = pessoaFisicaMapper.toEntity(pessoaFisicaRequest);

        Cliente clienteSalvar = pessoaFisicaSalvar.getCliente();
        clienteSalvar.setTipoCliente(EnumTipoCliente.PF);
        pessoaFisicaSalvar.setCliente(clienteSalvar);
        pessoaFisicaSalvar.setId(null);

        pessoaFisicaSalvar = this.pessoaFisicaRepository.save(pessoaFisicaSalvar);

        Contato contatoFind = pessoaFisicaSalvar.getCliente().getContato();
        List<Email> listEmailsSalvar = pessoaFisicaRequest.emails();
        List<Telefone> listTelefonesSalvar = pessoaFisicaRequest.telefones();
        List<Endereco> listEnderecosalvar = pessoaFisicaRequest.enderecos();

        emailService.createEmailByContato(contatoFind, listEmailsSalvar);
        telefoneService.createTelefoneByContato(contatoFind, listTelefonesSalvar);
        enderecoService.createEnderecoByContato(contatoFind, listEnderecosalvar);

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
        Contato contatoFind = clientFind.getContato();

        List<Email> emailsRequest = pessoaFisicaRequest.emails();
        List<Telefone> telefonesRequest = pessoaFisicaRequest.telefones();
        List<Endereco> enderecosRequest = pessoaFisicaRequest.enderecos();

        emailService.createEmailByContato(contatoFind, emailsRequest);
        telefoneService.createTelefoneByContato(contatoFind, telefonesRequest);
        enderecoService.createEnderecoByContato(contatoFind, enderecosRequest);

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

        if (!pessoaFisicaList.isEmpty()) {
            return pessoaFisicaMapper.toListDTO(pessoaFisicaList);
        }
        throw new RecursoNaoEncontradoException("Nenhum registro de pessoa física encontrado para esta pesquisa.");
    }

    public PessoaFisicaProjection findByCpf(String cpf) {
        if (cpf != null && !cpf.isEmpty()) {
            PessoaFisicaProjection pessoaFisicaProjection = pessoaFisicaRepository.findByCpf(cpf)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de pessoa física não encontrado para p " +
                            " cpf [" + cpf + "] informado."));
            return pessoaFisicaProjection;
        } else {
            throw new RegraNegocioException("É necessário informar um CPF para esta consulta de pessoa física.");
        }
    }

    public List<PessoaFisicaProjection> findByNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            List<PessoaFisicaProjection> pessoaFisicaProjections = pessoaFisicaRepository.findAllByName(nome.toUpperCase());
            if (!pessoaFisicaProjections.isEmpty()) {
                return pessoaFisicaProjections;
            } else {
                throw new RecursoNaoEncontradoException("Nenhum registro encontrado para este nome informado.");
            }
        } else {
            throw new RegraNegocioException("É necessário informar o nome para esta consulta de pessoa física.");
        }
    }

    public PessoaFisicaProjection findByEmailOrCpf(String emailCpf) {
        if (emailCpf != null && !emailCpf.isEmpty()) {
            PessoaFisicaProjection pessoaFisica = pessoaFisicaRepository.findPessoaFisicaByEmailOrCpf(emailCpf)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de pessoa física não encontrado" +
                            " para o email [" + emailCpf + "] informado."));
            return pessoaFisica;
        } else {
            throw new RegraNegocioException("É necessário informar um email para a consulta desta pessoa física.");
        }
    }

    public void desativar(Long id) {
        if (id != null) {
            Cliente clienteFind = this.readPessoaFisicaEntityById(id).getCliente();
            clienteFind.setDataFim(ZonedDateTime.now());
            clienteService.update(clienteFind);
        } else {
            throw new RegraNegocioException("É necessário informar o identificador da pessoa física.");
        }
    }

    public void ativar(Long id) {
        if (id != null) {
            Cliente clienteFind = this.readPessoaFisicaEntityById(id).getCliente();
            clienteFind.setDataFim(null);
            clienteService.update(clienteFind);
        } else {
            throw new RegraNegocioException("É necessário informar o identificador da pessoa física.");
        }
    }
}
