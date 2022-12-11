package com.rogerioreis.desafio.service;

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

        Cliente clienteSalvo = this.clienteRepository.save(cliente);

        return clienteSalvo;

    }

    public Cliente readById(Long id) {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        return cliente;

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
}












