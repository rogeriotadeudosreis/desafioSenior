package com.rogerioreis.desafio.controller;


import com.rogerioreis.desafio.dto.ItemFormDto;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.service.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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
    public ResponseEntity<ItemFormDto> create(@RequestBody @Valid ItemFormDto itemFormDto, UriComponentsBuilder uriBuilder) {

        Item item = this.modelMapper.map(itemFormDto, Item.class);

        ItemFormDto dto = this.modelMapper.map(itemService.create(item), ItemFormDto.class);

        URI uri = uriBuilder.path("/itemPedidos/{id}").buildAndExpand(item.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);

    }


    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiResponse(code = 200, message = "Sucesso")
    @ApiOperation(value = "Consulta paginada de Itens de Pedido.", notes = "Consulta de itens de pedidos na base de dados com paginação.")
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
    @ApiOperation(value = "Consulta de Itens de Pedido pelo Id.", notes = "Consulta um registro de itens de pedidos na base de dados pelo seu identificador.")
    public ResponseEntity<ItemFormDto> readById(@PathVariable Long id) {

        return ResponseEntity.ok(this.modelMapper.map(itemService.readById(id), ItemFormDto.class));

    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Atualização de Itens de Pedido.", notes = "Atualiza um registro de itens de pedidos na base de dados.")
    public ResponseEntity<ItemFormDto> update(@PathVariable Long id, @Valid @RequestBody ItemFormDto itemFormDto) {

        Item item = this.modelMapper.map(itemFormDto, Item.class);

        ItemFormDto dto = this.modelMapper.map(itemService.update(id, item), ItemFormDto.class);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclusão de Itens de Pedido.", notes = "Exclui logicamente um registro de itens de pedidos na base de dados.")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        itemService.delete(id);

        return ResponseEntity.ok().build();

    }
}
