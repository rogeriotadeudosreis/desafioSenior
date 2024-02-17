package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.dto.PessoaFisicaRequest;
import com.rogerioreis.desafio.dto.PessoaFisicaResponse;
import com.rogerioreis.desafio.model.PessoaFisica;
import com.rogerioreis.desafio.services.PessoaFisicaService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pessoa-fisica")
@Slf4j
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Física", description = "Cadastro de pessoa física.")
    public ResponseEntity<PessoaFisicaResponse> create(@RequestBody @Valid PessoaFisicaRequest pessoaFisicaRequest) {

        return ResponseEntity.ok(pessoaFisicaService.create(pessoaFisicaRequest));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Física", description = "Consulta uma pessoa física e retorna um response.")
    public ResponseEntity<PessoaFisicaResponse> readPessoaFisicaResponseById(@PathVariable Long id) {
        log.debug("debug --> Ocorreu um erro ao buscar a pessoa física response com o ID: " + id);

        return ResponseEntity.ok(pessoaFisicaService.readPessoaFisicaResponseById(id));
    }

    @GetMapping(value = "/listar", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Física", description = "Consulta uma pessoa física e retorna um response.")
    public ResponseEntity<List<PessoaFisicaResponse>> readPessoaFisicaResponseById() {
        log.debug("debug --> Ocorreu um erro ao buscar a lista de pessoa física response");

        List<PessoaFisicaResponse> responseList = pessoaFisicaService.readPessoaFisicaResponseList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping(value = "/pessoa-fisica/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Física", description = "Consulta e retorna uma pessoa física entidade.")
    public ResponseEntity<PessoaFisica> readPessoaFisicaEntityById(@PathVariable Long id) {
        log.debug("debug --> Ocorreu um erro ao buscar a pessoa física com o ID: " + id);

        return ResponseEntity.ok().body(pessoaFisicaService.readPessoaFisicaEntityById(id));
    }

    @PutMapping(path = "/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Física", description = "Atualizar um cadastro de pessoa física.")
    public ResponseEntity<PessoaFisicaResponse> update(@PathVariable Long id,@RequestBody @Valid PessoaFisicaRequest pessoaFisicaRequest) {

        pessoaFisicaService.update(id, pessoaFisicaRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    @Schema(name = "Pessoa Física", description = "Deleta uma pessoa física.")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        pessoaFisicaService.deletarById(id);
        log.debug("debug --> Ocorreu um erro ao buscar a pessoa física com o ID: " + id);

        return ResponseEntity.ok().build();
    }
}
