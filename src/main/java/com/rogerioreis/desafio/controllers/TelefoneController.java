package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.dto.TelefoneRequest;
import com.rogerioreis.desafio.dto.TelefoneResponse;
import com.rogerioreis.desafio.model.Telefone;
import com.rogerioreis.desafio.services.TelefoneService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/telefone")
public class TelefoneController {

    @Autowired
    private TelefoneService telefoneService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "telefone", description = "Cadastro de telefone.")
    public ResponseEntity<TelefoneResponse> create(@RequestBody TelefoneRequest telefoneRequest) {

        return ResponseEntity.ok(telefoneService.create(telefoneRequest));
    }

    @GetMapping(value = "/responses/{contatoId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "telefones", description = "Consulta e retorna uma lista de telefones a partir do identificador do contato.")
    public ResponseEntity<List<TelefoneResponse>> findAllTRelefonesResponsesByContatoId(@PathVariable Long contatoId) {
        return ResponseEntity.ok(telefoneService.findAllTelefonesResponsesByContatoId(contatoId));
    }

    @GetMapping(value = "/{contatoId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "telefones", description = "Consulta e retorna uma lista de telefones entity a partir do identificador do contato.")
    public ResponseEntity<List<Telefone>> findAllTelefonesByContatoId(@PathVariable Long contatoId) {
        return ResponseEntity.ok(telefoneService.findAllTelefonesByContatoId(contatoId));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Telefone", description = "Consulta e retorna um telefone pelo seu identificador.")
    public ResponseEntity<Telefone> findTelefoneById(@PathVariable Long id) {
        return ResponseEntity.ok(telefoneService.findTelefoneById(id));
    }

    @GetMapping(value = "/response/{id}")
    public ResponseEntity<TelefoneResponse> findTelefoneResponseById(@PathVariable Long id) {
        return ResponseEntity.ok(telefoneService.findTelefoneResponseById(id));
    }

    @DeleteMapping
    @Schema(name = "Telefone", description = "Deleta um telefone a partir de um objeto telefone informado.")
    public ResponseEntity<?> deleteTelefoneById(@RequestBody TelefoneRequest telefoneRequest) {
        telefoneService.deleteTelefone(telefoneRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/inserir-telefones", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "telefones", description = "Recebe uma lista de telefones e salva no banco um a um.")
    public ResponseEntity<List<TelefoneResponse>> createTelefones(@RequestBody List<TelefoneRequest> telefoneRequest) {

        return ResponseEntity.ok(telefoneService.createTelefones(telefoneRequest));
    }

}
