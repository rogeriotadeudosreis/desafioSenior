package com.rogerioreis.desafio.service;

import com.rogerioreis.desafio.dto.ClienteResponse;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.mapper.ClienteMapper;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    public void update(Cliente cliente) {
        if (cliente.getId() == null) {
            throw new RecursoNaoEncontradoException("É necessário informar o [id] da entidade [Pessoa]" +
                    "para atualizar");
        }
        Cliente clienteUpdate = cliente;
        clienteUpdate.setId(cliente.getId());
        clienteRepository.save(clienteUpdate);
    }

    public ClienteResponse readResponseClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente com id [" + id + "] não encontrada"));

        return clienteMapper.toDTO(cliente);
    }

    public List<ClienteResponse> readResponseClienteAll() {
        List<Cliente> clienteList = clienteRepository.findAll();
        if (clienteList == null || clienteList.isEmpty()) {
            throw new RecursoNaoEncontradoException("A lista de clientes está nula ou vazia.");
        }
        return clienteMapper.toListDTO(clienteList);
    }

    public List<Cliente> readClienteAll() {
        List<Cliente> clienteList = clienteRepository.findAll();
        if (clienteList == null || clienteList.isEmpty()) {
            throw new RecursoNaoEncontradoException("A lista de clientes está nula ou vazia.");
        }
        return clienteList;
    }

    public Cliente readClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente com id [" + id + "] não encontrada"));
    }
}
