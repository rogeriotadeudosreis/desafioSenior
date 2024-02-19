package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.PessoaJuridicaRequest;
import com.rogerioreis.desafio.enuns.EnumTipoCliente;
import com.rogerioreis.desafio.mapper.PessoaJuridicaMapper;
import com.rogerioreis.desafio.model.*;
import com.rogerioreis.desafio.repositories.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaJuridicaMapper pessoaJuridicaMapper;

    public PessoaJuridica create(PessoaJuridicaRequest pessoaJuridicaRequest) {
        PessoaJuridica pessoaJuridicaSalvar = pessoaJuridicaMapper.toEntity(pessoaJuridicaRequest);

        Cliente clienteSalvar = pessoaJuridicaSalvar.getCliente();
        clienteSalvar.setTipoCliente(EnumTipoCliente.PJ);
        pessoaJuridicaSalvar.setCliente(clienteSalvar);
        pessoaJuridicaSalvar.setId(null);

        pessoaJuridicaSalvar = this.pessoaRepository.save(pessoaJuridicaSalvar);

        Cliente clienteRetorno = pessoaJuridicaSalvar.getCliente();
        Contato contato = pessoaJuridicaSalvar.getCliente().getContato();
        List<Email> emails = pessoaJuridicaRequest.emails();
        List<Telefone> telefones = pessoaJuridicaRequest.telefones();
        List<Endereco> enderecos = pessoaJuridicaRequest.enderecos();

        emailService.createEmailByContato(contato, emails);
        telefoneService.createTelefoneByContato(contato, telefones);
        enderecoService.createEnderecoByCliente(clienteRetorno,enderecos);

        return pessoaJuridicaSalvar;
    }

}
