package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.dto.PessoaJuridicaRequest;
import com.rogerioreis.desafio.dto.PessoaJuridicaResponse;
import com.rogerioreis.desafio.services.PessoaJuridicaService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pessoa-juridica")
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Jurídica", description = "Cadastro de pessoa jurídica.")
    public ResponseEntity<PessoaJuridicaResponse> create(@RequestBody PessoaJuridicaRequest pessoaJuridicaRequest) {

        return ResponseEntity.ok(pessoaJuridicaService.create(pessoaJuridicaRequest));
    }

    @PutMapping(value = "/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(description = "Atualizar um registro de cliente pessoa jurídica.")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PessoaJuridicaRequest pessoaJuridicaRequest) {
        pessoaJuridicaService.update(id, pessoaJuridicaRequest);
        return ResponseEntity.ok().build();
    }
}
