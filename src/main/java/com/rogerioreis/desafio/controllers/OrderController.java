package com.rogerioreis.desafio.controllers;


import com.rogerioreis.desafio.dto.OrderResponse;
import com.rogerioreis.desafio.dto.OrderRequest;
import com.rogerioreis.desafio.model.Order;
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
public class OrderController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiResponse(responseCode = "201", description = "Pedido criado.")
    @Operation(summary = "Cadastro de Pedido.")
    public ResponseEntity<Order> create(@RequestBody @Valid OrderRequest pedidoFormDto, UriComponentsBuilder uriBuilder) {

        Order pedido = this.modelMapper.map(pedidoFormDto, Order.class);

        pedidoService.create(pedido);

        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).body(pedido);

    }

    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @Operation(summary = "Consulta paginada de Pedido.")
    public ResponseEntity<Page> page(
            @RequestParam(required = false) String descricao,
            @RequestParam(value = "0") Integer page,
            @RequestParam(value = "20") Integer size) {

        Page<Order> list = pedidoService.page(descricao, PageRequest.of(page, size));

        HttpStatus status = HttpStatus.OK;

        return ResponseEntity.status(status).body(list);

    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @Operation(summary = "Consulta de Pedido.")
    public ResponseEntity<OrderResponse> readById(@PathVariable Long id) {

        OrderResponse dto = this.modelMapper.map(pedidoService.readById(id), OrderResponse.class);

        return ResponseEntity.ok(dto);

    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso.")
    @Operation(summary = "Atualização de Pedido.")
    public ResponseEntity<Order> update(@PathVariable Long id, @Valid @RequestBody OrderRequest pedidoFormDto) {

        Order pedido = this.modelMapper.map(pedidoFormDto, Order.class);

        return ResponseEntity.ok(pedidoService.update(id, pedido));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclusão de Pedido.")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        pedidoService.delete(id);

        return ResponseEntity.ok().build();

    }
}
