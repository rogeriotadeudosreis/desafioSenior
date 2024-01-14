//package com.rogerioreis.desafio.controllers;
//
//import com.rogerioreis.desafio.dto.ClientResponse;
//import com.rogerioreis.desafio.dto.ClientRequest;
//import com.rogerioreis.desafio.model.Client;
//import com.rogerioreis.desafio.services.ClienteService;
//import io.swagger.v3.oas.annotations.Operation;
//import jakarta.validation.Valid;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.net.URI;
//
//@RestController
//@RequestMapping(value = "/api/clientes")
//public class ClientController {
//
//    @Autowired
//    private ClienteService clienteService;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Transactional
//    @Operation(summary= "Cadastro de Cliente.")
//    public ResponseEntity<ClientRequest> create(@RequestBody @Valid ClientRequest clienteRequest, UriComponentsBuilder uriBuilder) {
//
//        Client clienteSalvo = this.modelMapper.map(clienteFormDto, Client.class);
//
//        clienteService.create(clienteSalvo);
//
//        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteSalvo.getId()).toUri();
//
//        ClientRequest clienteDto = this.modelMapper.map(clienteSalvo, ClientRequest.class);
//
//        return ResponseEntity.created(uri).body(clienteDto);
//
//    }
//
//    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Transactional
//    @Operation(summary= "Consulta Paginada de Cliente.")
//    public ResponseEntity<Page> page(
//            @RequestParam(required = false) String descricao,
//            @RequestParam(defaultValue = "0") Integer page,
//            @RequestParam(defaultValue = "20") Integer size) {
//
//        Page<Client> list = clienteService.page(descricao, PageRequest.of(page, size));
//
//        HttpStatus status = HttpStatus.OK;
//
//        return ResponseEntity.status(status).body(list);
//
//    }
//
//    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Transactional
//    @Operation(summary= "Consulta de Cliente por id.")
//    public ResponseEntity<ClientResponse> readById(@PathVariable Long id) {
//
//        return ResponseEntity.ok(new ClientResponse(this.modelMapper.map(clienteService.readById(id), ClientResponse.class)));
//
//    }
//
//    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Transactional
//    @Operation(summary= "Atualização de Cliente.")
//    public ResponseEntity<ClientResponse> update(@PathVariable Long id, @Valid @RequestBody ClientRequest clienteForm) {
//
//        Client clienteAtualizado = this.modelMapper.map(clienteForm, Client.class);
//        clienteAtualizado.setId(id);
//
//        ClientResponse dto = new ClientResponse(this.modelMapper
//                .map(this.clienteService.update(clienteAtualizado), ClientResponse.class));
//
//        return ResponseEntity.ok(dto);
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary= "Exclusão de Cliente.")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//
//        clienteService.delete(id);
//
//        return ResponseEntity.ok().build();
//
//    }
//
//}
