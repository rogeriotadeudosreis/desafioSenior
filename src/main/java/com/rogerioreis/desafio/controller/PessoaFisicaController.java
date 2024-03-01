package com.rogerioreis.desafio.controller;

import com.rogerioreis.desafio.dto.PessoaFisicaRequest;
import com.rogerioreis.desafio.dto.PessoaFisicaResponse;
import com.rogerioreis.desafio.model.PessoaFisica;
import com.rogerioreis.desafio.projection.PessoaFisicaProjection;
import com.rogerioreis.desafio.service.PessoaFisicaService;
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

    @GetMapping(value = "/id/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Física", description = "Consulta uma pessoa física e retorna um response.")
    public ResponseEntity<PessoaFisicaResponse> readPessoaFisicaResponseById(@PathVariable("id") Long id) {
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
    public ResponseEntity<PessoaFisica> readPessoaFisicaEntityById(@PathVariable("id") Long id) {
        log.debug("debug --> Ocorreu um erro ao buscar a pessoa física com o ID: " + id);

        return ResponseEntity.ok().body(pessoaFisicaService.readPessoaFisicaEntityById(id));
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Física", description = "Atualizar um cadastro de pessoa física.")
    public ResponseEntity<PessoaFisicaResponse> update(@PathVariable("id") Long id, @RequestBody @Valid PessoaFisicaRequest pessoaFisicaRequest) {

        pessoaFisicaService.update(id, pessoaFisicaRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    @Schema(name = "Pessoa Física", description = "Deleta uma pessoa física.")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        pessoaFisicaService.deletarById(id);
        log.debug("debug --> Ocorreu um erro ao buscar a pessoa física com o ID: " + id);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/cpf/{cpf}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Física", description = "Consultar e retorna uma pessoa física pelo seu CPF.")
    public ResponseEntity<PessoaFisicaProjection> readByCpf(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(pessoaFisicaService.findByCpf(cpf));
    }

    @GetMapping(value = "/name")
    @Schema(name = "Pessoa Física", description = "Consulta e retorna uma pessoa física pelo nome.")
    public ResponseEntity<List<PessoaFisicaProjection>> readByName(@RequestParam("name") @Valid String nome) {
        return ResponseEntity.ok(pessoaFisicaService.findByNome(nome));
    }

    @GetMapping(value = "/email-cpf", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "Pessoa Física", description = "Consulta e retorna uma pessoa física pelo email ou cpf.")
    public ResponseEntity<PessoaFisicaProjection> readByEmail(@RequestParam("emailCpf") String emailCpf) {
        return ResponseEntity.ok(pessoaFisicaService.findByEmailOrCpf(emailCpf));
    }

    @PutMapping(value = "/desativar/{id}")
    public ResponseEntity<?> desativarAtivarPessoaFisicaById(@PathVariable Long id) {
        pessoaFisicaService.desativar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/ativar/{id}")
    public ResponseEntity<?> ativarAtivarPessoaFisicaById(@PathVariable Long id) {
        pessoaFisicaService.ativar(id);
        return ResponseEntity.ok().build();
    }
}
