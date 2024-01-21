package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.dto.ClientResponse;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @Operation(summary = "Cadastro de Cliente.")
    public ResponseEntity<ClientResponse> create(@RequestBody @Valid Cliente client, UriComponentsBuilder uriBuilder) {

        Cliente clientSave = clienteService.create(client);

        ClientResponse response = new ClientResponse(clientSave.getNome(), clientSave.getEmail(), clientSave.isAtivo());

        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clientSave.getId()).toUri();

        return ResponseEntity.created(uri).body(response);

    }

    @ResponseStatus()
    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @Operation(summary = "Consulta Paginada de Cliente.")
    public Page<ClientResponse> page(@RequestParam(required = false) String descricao,
                                     @RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "20") Integer size) {

        Page<Cliente> listClients = clienteService.page(descricao, PageRequest.of(page, size));

        return listClients.map(client -> new ClientResponse(client.getNome(), client.getEmail(), client.isAtivo()));

    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @Operation(summary = "Consulta de Cliente por id.")
    public ResponseEntity<ClientResponse> readById(@PathVariable Long id) {

        Cliente clientFind = clienteService.readById(id);

        return ResponseEntity.ok(new ClientResponse(clientFind.getNome(), clientFind.getEmail(), clientFind.isAtivo()));

    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @Operation(summary = "Atualização de Cliente.")
    public ResponseEntity<ClientResponse> update(@PathVariable Long id, @Valid @RequestBody Cliente client) {

        Cliente clientUpdate = client;
        clientUpdate.setId(id);

        clientUpdate = clienteService.update(clientUpdate);

        ClientResponse response = new ClientResponse(clientUpdate.getNome(), client.getEmail(), client.isAtivo());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de Cliente.")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        clienteService.delete(id);

        return ResponseEntity.ok().build();

    }

}
