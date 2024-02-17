package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.dto.PessoaResponse;
import com.rogerioreis.desafio.model.Pessoa;
import com.rogerioreis.desafio.services.PessoaService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping(value = "/response/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "pessoa", description = "Consulta um registro de pessoa.")
    public ResponseEntity<PessoaResponse> readById(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.readById(id));
    }

    @GetMapping(value = "/entity/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "pessoa", description = "Consulta um registro de pessoa.")
    public ResponseEntity<Pessoa> readPessoaById(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.readPessoaById(id));
    }

    @GetMapping(value = "/response",produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "pessoa", description = "Consulta uma lista de pessoas.")
    public ResponseEntity<List<PessoaResponse>> readAll(){
        return ResponseEntity.ok(pessoaService.readAll());
    }
}
