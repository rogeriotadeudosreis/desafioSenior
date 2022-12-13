package com.rogerioreis.desafio.controller;

import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @ApiOperation(value = "Cadastro de Cliente.", notes = "Armazena um registro de cliente na base de dados." )
    public ResponseEntity<Cliente> create(@RequestBody @Valid Cliente clienteForm, UriComponentsBuilder uriBuilder) {

        Cliente cliente = clienteService.create(clienteForm);

        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(cliente);

    }

    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Consulta Paginada de Cliente.", notes = "Consulta de cliente na base de dados com paginação." )
    public ResponseEntity<Page> page(
            @RequestParam(required = false) String descricao,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size){

        Page<Cliente> list = clienteService.page(descricao, PageRequest.of(page,size));

        HttpStatus status = HttpStatus.OK;

        return ResponseEntity.status(status).body(list);

    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Consulta de Cliente por id.", notes = "Consulta um registro de cliente na base de dados pelo identificador." )
    public ResponseEntity<Cliente> readById(@PathVariable Long id){

        return ResponseEntity.ok(clienteService.readById(id));

    }

    @PutMapping(value = "/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Atualização de Cliente.", notes = "Atualiza um registro de cliente na base de dados." )
    public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody Cliente clienteForm){

        return ResponseEntity.ok(clienteService.update(id, clienteForm));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclusão de Cliente.", notes = "Exclui logicamente um registro de cliente na base de dados." )
    public ResponseEntity<?> delete (@PathVariable Long id){

        clienteService.delete(id);

        return ResponseEntity.ok().build();

    }

}
