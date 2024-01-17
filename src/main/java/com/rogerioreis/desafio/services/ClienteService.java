package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.ClientResponse;
import com.rogerioreis.desafio.exception.RecursoExistenteException;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RequisicaoComErroException;
import com.rogerioreis.desafio.model.Client;
import com.rogerioreis.desafio.repositories.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Client create(Client cliente) {

        cliente.setId(null);

        this.validaCliente(cliente);

        Client clienteSalvo = this.clienteRepository.save(cliente);

        return clienteSalvo;

    }

    public Client readById(Long id) {

        return clienteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Cliente com [" + id + "] não encontrado."));

    }

    public Page<Client> page(String descricao, Pageable pageable) {

        String desc = descricao != null ? descricao : "";

        return clienteRepository.findAllByNomeLikeIgnoreCaseOrEmailIgnoreCase(desc, pageable);
    }

    public List<ClientResponse> list() {
        List<Client> list = clienteRepository.findAll();
        return list.stream().map(entity -> this.modelMapper.map(entity, ClientResponse.class)).collect(Collectors.toList());
    }

    public Client update(Client clienteForm) {

        this.readById(clienteForm.getId());

        this.validaCliente(clienteForm);

        return clienteRepository.save(clienteForm);

    }

    public void delete(Long id) {

        Client cliente = readById(id);

        cliente.setFimVigencia(ZonedDateTime.now());

        this.clienteRepository.save(cliente);

    }

//    Validando o cadastro de cliente
    public void validaCliente(Client cliente) {

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
            Optional<Client> cliConsultado = this.clienteRepository.findClienteByEmailIgnoreCase(cliente.getEmail());

            cliConsultado.ifPresent(cliente1 -> {
                if (!cliente1.getId().equals(cliente.getId())) {
                    throw new RecursoExistenteException("Já existe um cliente cadastrado com o email [ " + cliente.getEmail() + " ] informado.");
                }
            });
        }
    }
}












