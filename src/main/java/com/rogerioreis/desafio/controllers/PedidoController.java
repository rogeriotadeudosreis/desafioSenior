package com.rogerioreis.desafio.controllers;


import com.rogerioreis.desafio.model.Pedido;
import com.rogerioreis.desafio.services.ItemService;
import com.rogerioreis.desafio.services.PedidoService;
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
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiResponse(responseCode = "201", description = "Pedido criado.")
    @Operation(summary = "Cadastro de Pedido.")
    public ResponseEntity<Pedido> create(@RequestBody @Valid Pedido pedidoForm, UriComponentsBuilder uriBuilder) {

        Pedido pedidoSalve = pedidoForm;

        pedidoSalve.setId(null);

        Pedido salvo = pedidoService.create(pedidoSalve);

        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(salvo.getId()).toUri();

        return ResponseEntity.created(uri).body(salvo);

    }

    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @Operation(summary = "Consulta paginada de Pedido.")
    public ResponseEntity<Page> page(
            @RequestParam(required = false) String descricao,
            @RequestParam(value = "0") Integer page,
            @RequestParam(value = "20") Integer size) {

        Page<Pedido> list = pedidoService.page(descricao, PageRequest.of(page, size));

        HttpStatus status = HttpStatus.OK;

        return ResponseEntity.status(status).body(list);

    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @Operation(summary = "Consulta de Pedido.")
    public ResponseEntity<Pedido> readById(@PathVariable Long id) {

        Pedido dto = this.modelMapper.map(pedidoService.readById(id), Pedido.class);

        return ResponseEntity.ok(dto);

    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso.")
    @Operation(summary = "Atualização de Pedido.")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @Valid @RequestBody Pedido pedido) {

//        Pedido pedido = this.modelMapper.map(pedido, Pedido.class);

        Pedido pedidoUpdate = pedidoService.update(id, pedido);
        return ResponseEntity.ok(pedidoUpdate);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de Pedido.")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        pedidoService.delete(id);

        return ResponseEntity.ok().build();

    }
}
