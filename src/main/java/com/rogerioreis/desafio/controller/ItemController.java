package com.rogerioreis.desafio.controller;


import com.rogerioreis.desafio.dto.ItemPedidoFormDto;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.service.ItemService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
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
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Cadastro de Itens de Pedido.", notes = "Armazena um registro de itens de pedidos na base de dados.")
    public ResponseEntity<ItemPedidoFormDto> create(@RequestBody @Valid ItemPedidoFormDto itemPedidoFormDto, UriComponentsBuilder uriBuilder) {

        Item item = this.modelMapper.map(itemPedidoFormDto, Item.class);

        ItemPedidoFormDto dto = this.modelMapper.map(itemService.create(item), ItemPedidoFormDto.class);

        URI uri = uriBuilder.path("/itemPedidos/{id}").buildAndExpand(item.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);

    }


    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Consulta paginada de Itens de Pedido.", notes = "Consulta de itens de pedidos na base de dados com paginação.")
    public ResponseEntity<Page> page(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size) {

        Page<Item> list = itemService.page(PageRequest.of(page, size));

        HttpStatus status = HttpStatus.OK;

        return ResponseEntity.status(status).body(list);

    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Consulta de Itens de Pedido pelo Id.", notes = "Consulta um registro de itens de pedidos na base de dados pelo seu identificador.")
    public ResponseEntity<Item> readById(@PathVariable Long id) {

        return ResponseEntity.ok(itemService.readById(id));

    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Atualização de Itens de Pedido.", notes = "Atualiza um registro de itens de pedidos na base de dados.")
    public ResponseEntity<Item> update(@PathVariable Long id, @Valid @RequestBody Item itemForm) {

        return ResponseEntity.ok(itemService.update(id, itemForm));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclusão de Itens de Pedido.", notes = "Exclui logicamente um registro de itens de pedidos na base de dados.")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        itemService.delete(id);

        return ResponseEntity.ok().build();

    }
}
