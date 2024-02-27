package com.rogerioreis.desafio.controller;

import com.rogerioreis.desafio.dto.ClienteResponse;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.service.ClienteService;
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
    public ResponseEntity<ClienteResponse> readResponseClienteById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.readResponseClienteById(id));
    }

    @GetMapping(value = "/entity/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "cliente", description = "Consulta um registro de cliente.")
    public ResponseEntity<Cliente> readClienteById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.readClienteById(id));
    }

    @GetMapping(value = "/response",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "cliente", description = "Consulta uma lista de clientes.")
    public ResponseEntity<List<ClienteResponse>> readResponseClienteAll(){
        return ResponseEntity.ok(clienteService.readResponseClienteAll());
    }

    @GetMapping(value = "/entity",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "cliente", description = "Consulta uma lista de entidade clientes.")
    public ResponseEntity<List<Cliente>> readClienteAll(){
        return ResponseEntity.ok(clienteService.readClienteAll());
    }
}
