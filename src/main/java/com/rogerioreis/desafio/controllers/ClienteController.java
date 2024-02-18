package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.dto.ClienteResponse;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.services.ClienteService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/response/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "cliente", description = "Consulta um registro de cliente.")
    public ResponseEntity<ClienteResponse> readById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.readById(id));
    }

    @GetMapping(value = "/entity/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "cliente", description = "Consulta um registro de cliente.")
    public ResponseEntity<Cliente> readPessoaById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.readPessoaById(id));
    }

    @GetMapping(value = "/response",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "cliente", description = "Consulta uma lista de clientes.")
    public ResponseEntity<List<ClienteResponse>> readAll(){
        return ResponseEntity.ok(clienteService.readAll());
    }
}
