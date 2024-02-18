package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.PessoaJuridicaRequest;
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
    private PessoaJuridicaMapper pessoaJuridicaMapper;

    public PessoaJuridica create(PessoaJuridicaRequest pessoaJuridicaRequest) {
        PessoaJuridica pessoaJuridicaSalvar = pessoaJuridicaMapper.toEntity(pessoaJuridicaRequest);

        Cliente cliente = new Cliente();
        cliente.setTipoCliente("PJ");
        pessoaJuridicaSalvar.setCliente(cliente);
        pessoaJuridicaSalvar.setId(null);

        pessoaJuridicaSalvar = this.pessoaRepository.save(pessoaJuridicaSalvar);

        Contato contato = pessoaJuridicaSalvar.getCliente().getContato();
        List<Email> emails = pessoaJuridicaRequest.emails();
        List<Telefone> telefones = pessoaJuridicaRequest.telefones();

        emailService.createEmailByContato(contato, emails);
        telefoneService.createTelefoneByContato(contato, telefones);

        return pessoaJuridicaSalvar;
    }

}
