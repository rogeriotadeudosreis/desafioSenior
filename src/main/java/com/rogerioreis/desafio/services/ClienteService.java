package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.ClienteResponse;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.mapper.ClienteMapper;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.repositories.ClienteRepository;
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

    public ClienteResponse readById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de pessoa não encontro para o id: " + id));

        return clienteMapper.toDTO(cliente);
    }

    public List<ClienteResponse> readAll() {
        List<Cliente> clienteList = clienteRepository.findAll();
        return clienteMapper.toListDTO(clienteList);
    }

    public Cliente readPessoaById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de pessoa não encontro para o id: " + id));
    }
}
