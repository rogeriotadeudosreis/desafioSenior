package com.rogerioreis.desafio.controllers;

import com.rogerioreis.desafio.dto.EmailRequest;
import com.rogerioreis.desafio.dto.EmailResponse;
import com.rogerioreis.desafio.model.Contato;
import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.services.EmailService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "email", description = "Cadastro de emails.")
    public ResponseEntity<EmailResponse> create(@RequestBody EmailRequest email) {

        return ResponseEntity.ok(emailService.create(email));
    }

    @PostMapping(value = "/inserir-lista",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(description = "Cria emails através de um contato informado e uma lista de emails com no mínimo 01 item na lista.")
    public ResponseEntity<List<EmailResponse>> createEmails(@RequestBody List<EmailRequest> emails) {

        return ResponseEntity.ok(emailService.createEmails(emails));
    }

    @GetMapping(value = "/{idContato}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(name = "email", description = "Busca uma lista de emails a partir de um contato.")
    public ResponseEntity<List<Email>> listByContato(@PathVariable Long idContato) {

        List<Email> emailList = emailService.findAllEmailsByContatoId(idContato);

        return ResponseEntity.ok(emailList);
    }

    @GetMapping(value = "/responses/{idContato}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(description = "Consulta e retorna uma lista de emails response pelo identificador do contato.")
    public ResponseEntity<List<EmailResponse>> readAllEmailsResponses(@PathVariable Long idContato) {

        List<EmailResponse> emailResponseList = emailService.findAllEmailsResponsesByContatoId(idContato);

        return ResponseEntity.ok(emailResponseList);
    }

    @GetMapping(value = "/response/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Schema(description = "Consulta um email pelo seu identificador e retorna um response desse email.")
    public ResponseEntity<EmailResponse> readEmailResponseById(@PathVariable Long id) {

        return ResponseEntity.ok(emailService.findEmailResponseById(id));

    }

    @DeleteMapping
    @Schema(description = "Deleta um registro de email pelo seu identificador.")
    public ResponseEntity<?> deleteById(@RequestBody EmailRequest emailRequest) {
        emailService.deleteById(emailRequest);
        return ResponseEntity.ok().build();
    }

}
