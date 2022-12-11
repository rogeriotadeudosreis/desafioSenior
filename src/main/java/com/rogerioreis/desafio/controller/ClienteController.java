package com.rogerioreis.desafio.controller;

import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<Cliente> create(@RequestBody @Valid Cliente clienteForm, UriComponentsBuilder uriBuilder) {

        Cliente cliente = clienteService.create(clienteForm);

        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(cliente);

    }

    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<Page> page(@RequestParam(required = false) String descricao,
                              @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 20) Pageable page) {

        Page<Cliente> list = clienteService.page(descricao, page);

        HttpStatus status = HttpStatus.OK;

        return ResponseEntity.status(status).body(list);

    }


    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<Cliente> readById(@PathVariable Long id){

        return ResponseEntity.ok(clienteService.readById(id));

    }

    @PutMapping(value = "/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody Cliente clienteForm){

        return ResponseEntity.ok(clienteService.update(id, clienteForm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){

        clienteService.delete(id);

        return ResponseEntity.ok().build();

    }

}
