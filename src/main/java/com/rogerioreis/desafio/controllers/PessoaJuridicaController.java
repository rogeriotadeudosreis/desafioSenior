package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.model.PessoaJuridica;
import com.rogerioreis.desafio.services.PessoaJuridicaService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pessoa-juridica")
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Jurídica", description = "Cadastro de pessoa jurídica.")
    public ResponseEntity<PessoaJuridica> create(@RequestBody PessoaJuridica pessoaJuridica) {

        PessoaJuridica pessoaJuridicaSalva = pessoaJuridicaService.create(pessoaJuridica);

        return ResponseEntity.ok(pessoaJuridicaSalva);
    }
}
