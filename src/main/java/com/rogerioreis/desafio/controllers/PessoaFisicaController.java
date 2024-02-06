package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.dto.PessoaFisicaRequest;
import com.rogerioreis.desafio.model.PessoaFisica;
import com.rogerioreis.desafio.services.PessoaFisicaService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pessoa-fisica")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Física", description = "Cadastro de pessoa física.")
    public ResponseEntity<PessoaFisica> create(@RequestBody @Valid PessoaFisicaRequest pessoaFisicaRequest) {

        PessoaFisica pessoaFisicaSalva = pessoaService.create(pessoaFisicaRequest);

        return ResponseEntity.ok(pessoaFisicaSalva);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Física", description = "Consulta uma pessoa física.")
    public ResponseEntity<PessoaFisica> create(@PathVariable Long id) {

        PessoaFisica pessoaFisica = pessoaService.findById(id);

        return ResponseEntity.ok(pessoaFisica);
    }
}
