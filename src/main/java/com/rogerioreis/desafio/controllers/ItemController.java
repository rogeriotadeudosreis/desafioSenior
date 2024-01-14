package com.rogerioreis.desafio.controllers;


import com.rogerioreis.desafio.dto.ItemRequest;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.services.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
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
    @Operation(summary = "Cadastro de Itens de Pedido.")
    public ResponseEntity<ItemRequest> create(@RequestBody @Valid ItemRequest itemFormDto, UriComponentsBuilder uriBuilder) {

        Item item = this.modelMapper.map(itemFormDto, Item.class);

        ItemRequest dto = this.modelMapper.map(itemService.create(item), ItemRequest.class);

        URI uri = uriBuilder.path("/itemPedidos/{id}").buildAndExpand(item.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);

    }


    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @Operation(summary = "Consulta paginada de Itens de Pedido.")
    public ResponseEntity<Page> page(
            @RequestParam(required = false) String descricao,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size) {

        Page<Item> list = itemService.page(descricao, PageRequest.of(page, size));

        HttpStatus status = HttpStatus.OK;

        return ResponseEntity.status(status).body(list);

    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @Operation(summary = "Consulta de Itens de Pedido pelo Id.")
    public ResponseEntity<ItemRequest> readById(@PathVariable Long id) {

        return ResponseEntity.ok(this.modelMapper.map(itemService.readById(id), ItemRequest.class));

    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @Operation(summary = "Atualização de Itens de Pedido.")
    public ResponseEntity<ItemRequest> update(@PathVariable Long id, @Valid @RequestBody ItemRequest itemFormDto) {

        Item item = this.modelMapper.map(itemFormDto, Item.class);

        ItemRequest dto = this.modelMapper.map(itemService.update(id, item), ItemRequest.class);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de Itens de Pedido.")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        itemService.delete(id);

        return ResponseEntity.ok().build();

    }
}
