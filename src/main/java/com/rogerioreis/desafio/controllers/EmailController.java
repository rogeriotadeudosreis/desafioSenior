package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.services.EmailService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "email", description = "Cadastro de emails.")
    public ResponseEntity<Email> create(@RequestBody Email email) {

        Email emailSalvo = emailService.create(email);

        return ResponseEntity.ok(emailSalvo);
    }

    @GetMapping(value = "/{idContato}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "email", description = "Busca uma lista de emails a partir de um contato.")
    public ResponseEntity<List<Email>> listByContato(@PathVariable Long idContato) {

        List<Email> emailList = emailService.findAllByContatoId(idContato);

        return ResponseEntity.ok(emailList);
    }
}
