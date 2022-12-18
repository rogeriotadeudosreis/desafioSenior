package com.rogerioreis.desafio.service;

import com.rogerioreis.desafio.exception.RecursoExistenteException;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.exception.RequisicaoComErroException;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente create(Cliente cliente) {

        cliente.setId(null);

        this.validaCliente(cliente);

        Cliente clienteSalvo = this.clienteRepository.save(cliente);

        return clienteSalvo;

    }

    public Cliente readById(Long id) {

        return clienteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente com [" + id + "] não encontrado."));

    }

    public Page<Cliente> page(String descricao, Pageable pageable) {

        String desc = descricao != null ? descricao : "";

        return clienteRepository.findAllByNomeLikeIgnoreCaseOrEmailIgnoreCase(desc, pageable);
    }

    public Cliente update(Cliente clienteForm) {

        this.readById(clienteForm.getId());

        this.validaCliente(clienteForm);

        return clienteRepository.save(clienteForm);

    }

    public void delete(Long id) {

        Cliente cliente = readById(id);

        cliente.setDataFim(ZonedDateTime.now());

        this.clienteRepository.save(cliente);

    }

    public void validaCliente(Cliente cliente) {

        if (cliente.getNome().trim().isEmpty())
            throw new RequisicaoComErroException("O NOME do cliente é obrigatório.");

        if (cliente.getEmail().trim().isEmpty())
            throw new RequisicaoComErroException("O EMAIL do cliente é obrigatório.");

        if (cliente.getId() == null) {
            boolean isClienteFind = clienteRepository.findClienteByEmailIgnoreCase(cliente.getEmail()).isPresent();
            if (isClienteFind) {
                throw new RecursoExistenteException("Já existe um cliente cadastrado com o código [ " + cliente.getEmail() + " ] informado.");
            }
        } else {
            Optional<Cliente> cliConsultado = this.clienteRepository.findClienteByEmailIgnoreCase(cliente.getEmail());

            cliConsultado.ifPresent(cliente1 -> {
                if (!cliente1.getId().equals(cliente.getId())) {
                    throw new RecursoExistenteException("Já existe um cliente cadastrado com o email [ " + cliente.getEmail() + " ] informado.");
                }
            });
        }
    }
}












