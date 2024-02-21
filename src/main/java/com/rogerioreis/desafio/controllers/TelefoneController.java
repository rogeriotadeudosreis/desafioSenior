package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.dto.TelefoneRequest;
import com.rogerioreis.desafio.dto.TelefoneResponse;
import com.rogerioreis.desafio.model.Telefone;
import com.rogerioreis.desafio.services.TelefoneService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
