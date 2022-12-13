package com.rogerioreis.desafio.controller;


import com.rogerioreis.desafio.model.ItemPedido;
import com.rogerioreis.desafio.service.ItemPedidoService;
import io.swagger.annotations.ApiOperation;
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

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Cadastro de Itens de Pedido.", notes = "Armazena um registro de itens de pedidos na base de dados.")
    public ResponseEntity<ItemPedido> create(@RequestBody @Valid ItemPedido itemPedidoForm, UriComponentsBuilder uriBuilder) {

        ItemPedido itemPedido = itemPedidoService.create(itemPedidoForm);

        URI uri = uriBuilder.path("/itemPedidos/{id}").buildAndExpand(itemPedido.getId()).toUri();

        return ResponseEntity.created(uri).body(itemPedido);

    }

    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Consulta paginada de Itens de Pedido.", notes = "Consulta de itens de pedidos na base de dados com paginação.")
    public ResponseEntity<Page> page(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size) {

        Page<ItemPedido> list = itemPedidoService.page(PageRequest.of(page, size));

        HttpStatus status = HttpStatus.OK;

        return ResponseEntity.status(status).body(list);

    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Consulta de Itens de Pedido pelo Id.", notes = "Consulta um registro de itens de pedidos na base de dados pelo seu identificador.")
    public ResponseEntity<ItemPedido> readById(@PathVariable Long id) {

        return ResponseEntity.ok(itemPedidoService.readById(id));

    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Atualização de Itens de Pedido.", notes = "Atualiza um registro de itens de pedidos na base de dados.")
    public ResponseEntity<ItemPedido> update(@PathVariable Long id, @Valid @RequestBody ItemPedido itemPedidoForm) {

        return ResponseEntity.ok(itemPedidoService.update(id, itemPedidoForm));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclusão de Itens de Pedido.", notes = "Exclui logicamente um registro de itens de pedidos na base de dados.")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        itemPedidoService.delete(id);

        return ResponseEntity.ok().build();

    }
}
