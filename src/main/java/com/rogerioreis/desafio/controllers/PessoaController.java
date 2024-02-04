package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.model.Pessoa;
import com.rogerioreis.desafio.services.PessoaService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

//    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Schema(name = "pessoa", description = "Cadastro de pessoa.")
//    public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa) {
//
//        Pessoa pessoaSalva = pessoaService.create(pessoa);
//
//        return ResponseEntity.ok(pessoaSalva);
//    }
}
