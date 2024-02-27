package com.rogerioreis.desafio.controller;

import com.rogerioreis.desafio.dto.EnderecoRequest;
import com.rogerioreis.desafio.dto.EnderecoResponse;
import com.rogerioreis.desafio.model.Endereco;
import com.rogerioreis.desafio.service.EnderecoService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Endereco", description = "Armazena um registro de endereço no banco de dados.")
    public ResponseEntity<EnderecoResponse> create(@RequestBody EnderecoRequest enderecoRequest) {
        return ResponseEntity.ok(enderecoService.create(enderecoRequest));
    }

    @GetMapping(value = "/responses/{contatoId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Endereco", description = "Consulta e retorna uma lista de endereços responses.")
    public ResponseEntity<List<EnderecoResponse>> readAllResponsesByContatoId(@PathVariable Long contatoId) {
        return ResponseEntity.ok(enderecoService.findAllEnderecosResponseByContatoId(contatoId));
    }

    @GetMapping(value = "/entitys/{contatoId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Endereco", description = "Consulta e retorna uma lista de endereços entitys.")
    public ResponseEntity<List<Endereco>> readAllEnderecosByContatoId(@PathVariable Long contatoId) {
        return ResponseEntity.ok(enderecoService.findAllEnderecosByContatoId(contatoId));
    }

    @GetMapping(value = "/entity/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Endereco", description = "Consulta um endereço entity específico pelo seu identificador.")
    public ResponseEntity<Endereco> readEnderecoById(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.findEnderecoById(id));
    }

    @GetMapping(value = "/response/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Endereco", description = "Consulta um endereço response específico pelo seu identificador.")
    public ResponseEntity<EnderecoResponse> readEnderecoResponseById(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.findEnderecoresponseById(id));
    }

    @DeleteMapping
    @Schema(name = "Endereco", description = "Deleta um endereço no banco de dados.")
    public ResponseEntity<?> delete(@RequestBody EnderecoRequest enderecoRequest) {
        enderecoService.deleteByEndereco(enderecoRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/inserir-lista", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Endereco", description = "Armazena uma lista de endereço no banco de dados.")
    public ResponseEntity<List<EnderecoResponse>> createEnderecos(@RequestBody List<EnderecoRequest> enderecoRequests) {
        return ResponseEntity.ok(enderecoService.createEnderecos(enderecoRequests));
    }
}









