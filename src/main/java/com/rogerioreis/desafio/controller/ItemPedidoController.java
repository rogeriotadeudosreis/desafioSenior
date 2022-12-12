package com.rogerioreis.desafio.controller;


import com.rogerioreis.desafio.model.ItemPedido;
import com.rogerioreis.desafio.service.ItemPedidoService;
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
@RequestMapping(value = "/api/itemPedidos")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @PostMapping(value = "/produto", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<ItemPedido> create(@RequestBody @Valid ItemPedido itemPedidoForm, UriComponentsBuilder uriBuilder) {

        ItemPedido itemPedido = itemPedidoService.create(itemPedidoForm);

        URI uri = uriBuilder.path("/itemPedidos/{id}").buildAndExpand(itemPedido.getId()).toUri();

        return ResponseEntity.created(uri).body(itemPedido);

    }

    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<Page> page(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size){

        Page<ItemPedido> list = itemPedidoService.page(PageRequest.of(page,size));

        HttpStatus status = HttpStatus.OK;

        return ResponseEntity.status(status).body(list);

    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<ItemPedido> readById(@PathVariable Long id){

        return ResponseEntity.ok(itemPedidoService.readById(id));

    }

    @PutMapping(value = "/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<ItemPedido> update(@PathVariable Long id, @Valid @RequestBody ItemPedido itemPedidoForm){

        return ResponseEntity.ok(itemPedidoService.update(id, itemPedidoForm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){

        itemPedidoService.delete(id);

        return ResponseEntity.ok().build();

    }
}
