package com.rogerioreis.desafio.controller;


import com.rogerioreis.desafio.model.Pedido;
import com.rogerioreis.desafio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<Pedido> create(@RequestBody @Valid Pedido pedidoForm, UriComponentsBuilder uriBuilder) {

        Pedido pedido = pedidoService.create(pedidoForm);

        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).body(pedido);

    }

    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<Page> page(
            @RequestParam(required = false) String descricao,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size){

        Page<Pedido> list = pedidoService.page(descricao, PageRequest.of(page,size));

        HttpStatus status = HttpStatus.OK;

        return ResponseEntity.status(status).body(list);

    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<Pedido> readById(@PathVariable Long id){

        return ResponseEntity.ok(pedidoService.readById(id));

    }

    @PutMapping(value = "/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<Pedido> update(@PathVariable Long id, @Valid @RequestBody Pedido pedidoForm){

        return ResponseEntity.ok(pedidoService.update(id, pedidoForm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){

        pedidoService.delete(id);

        return ResponseEntity.ok().build();

    }
}
