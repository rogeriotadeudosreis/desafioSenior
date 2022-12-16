package com.rogerioreis.desafio.service;

import com.rogerioreis.desafio.exception.RecursoExistenteException;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.exception.RequisicaoComErroException;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente create(Cliente cliente) {

        cliente.setId(null);

        validaClienteEmail(cliente);

        Cliente clienteSalvo = this.clienteRepository.save(cliente);

        return clienteSalvo;

    }

    public Cliente readById(Long id) {

        return clienteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado."));

    }

    public Page<Cliente> page(String descricao, Pageable pageable) {

        String desc = descricao != null ? descricao : "";

        return clienteRepository.findAllByNomeLikeIgnoreCaseOrEmailIgnoreCase(desc, pageable);
    }

    public Cliente update(Long id, Cliente clienteForm) {

        Cliente cliente = this.readById(id);

        clienteForm.setId(cliente.getId());

        return clienteRepository.save(clienteForm);

    }

    public void delete(Long id) {

        Cliente cliente = readById(id);

        cliente.setDataFim(ZonedDateTime.now());

        this.clienteRepository.save(cliente);

    }

    public void validaClienteEmail(Cliente cliente) {

        if (cliente.getEmail().isEmpty()) throw new RequisicaoComErroException("Email nulo");

        boolean isClienteFind = clienteRepository.findClienteByEmailIgnoreCase(cliente.getEmail()).isPresent();

        if (isClienteFind) {
            throw new RecursoExistenteException("" +
                    "Já existe um cliente cadastrado com o email [ " + cliente.getEmail() + " ] informado.");
        }
    }
}












