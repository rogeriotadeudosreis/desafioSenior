package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.dto.PessoaJuridicaRequest;
import com.rogerioreis.desafio.dto.PessoaJuridicaResponse;
import com.rogerioreis.desafio.model.PessoaJuridica;
import com.rogerioreis.desafio.services.PessoaJuridicaService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(description = "Atualizar um registro de cliente pessoa jurídica.")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PessoaJuridicaRequest pessoaJuridicaRequest) {
        pessoaJuridicaService.update(id, pessoaJuridicaRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(description = "Consulta um registro de pessoa jurídica informando seu identificador.")
    public ResponseEntity<PessoaJuridica> readPessoaJuridica(@PathVariable Long id) {

        log.debug("debug --> Ocorreu um erro ao buscar a pessoa física com o ID: " + id);
        PessoaJuridica pessoaJuridica = pessoaJuridicaService.readPessoaJuridicaById(id);

        return ResponseEntity.ok(pessoaJuridica);
    }

    @GetMapping(value = "/response/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(description = "Consulta um registro de pessoa jurídica informando seu identificador.")
    public ResponseEntity<PessoaJuridicaResponse> readPessoaJuridicaResponse(@PathVariable Long id) {

        log.debug("debug --> Ocorreu um erro ao buscar a pessoa física com o ID: " + id);
        PessoaJuridicaResponse pessoaJuridicaResponse = pessoaJuridicaService.readPessoaJuridicaResponseById(id);

        return ResponseEntity.ok(pessoaJuridicaResponse);
    }

    @DeleteMapping(value = "/{id}")
    @Schema(description = "Deleta um registro de pessoa jurídica na base de dados informando seu identificador.")
    public ResponseEntity<PessoaJuridica> deleteById(@PathVariable Long id) {
        pessoaJuridicaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
