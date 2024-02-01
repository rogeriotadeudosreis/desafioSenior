package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.model.PessoaFisica;
import com.rogerioreis.desafio.services.PessoaFisicaService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pessoa-fisica")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Física", description = "Cadastro de pessoa física.")
    public ResponseEntity<PessoaFisica> create(@RequestBody PessoaFisica pessoa) {

        PessoaFisica pessoaFisicaSalva = pessoaService.create(pessoa);

        return ResponseEntity.ok(pessoaFisicaSalva);
    }
}
