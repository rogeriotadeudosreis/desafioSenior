package com.rogerioreis.desafio.controller;

import com.rogerioreis.desafio.dto.ClienteConsultaDto;
import com.rogerioreis.desafio.dto.ClienteFormDto;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Cadastro de Cliente.", notes = "Armazena um registro de cliente na base de dados.")
    public ResponseEntity<ClienteFormDto> create(@RequestBody @Valid ClienteFormDto clienteFormDto, UriComponentsBuilder uriBuilder) {

        Cliente clienteSalvo = this.modelMapper.map(clienteFormDto, Cliente.class);

        clienteService.create(clienteSalvo);

        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteSalvo.getId()).toUri();

        ClienteFormDto clienteDto = this.modelMapper.map(clienteSalvo, ClienteFormDto.class);

        return ResponseEntity.created(uri).body(clienteDto);

    }

    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Consulta Paginada de Cliente.", notes = "Consulta de cliente na base de dados com paginação.")
    public ResponseEntity<Page> page(
            @RequestParam(required = false) String descricao,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size) {

        Page<ClienteConsultaDto> list = clienteService.page(descricao, PageRequest.of(page, size));

        HttpStatus status = HttpStatus.OK;

        return ResponseEntity.status(status).body(list);

    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Consulta de Cliente por id.", notes = "Consulta um registro de cliente na base de dados pelo identificador.")
    public ResponseEntity<ClienteConsultaDto> readById(@PathVariable Long id) {

        return ResponseEntity.ok(new ClienteConsultaDto(this.modelMapper.map(clienteService.readById(id), ClienteConsultaDto.class)));

    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Atualização de Cliente.", notes = "Atualiza um registro de cliente na base de dados.")
    public ResponseEntity<ClienteConsultaDto> update(@PathVariable Long id, @Valid @RequestBody ClienteFormDto clienteForm) {

        Cliente clienteAtualizado = this.modelMapper.map(clienteForm, Cliente.class);
        clienteAtualizado.setId(id);

        ClienteConsultaDto dto = new ClienteConsultaDto(this.modelMapper
                .map(this.clienteService.update(clienteAtualizado), ClienteConsultaDto.class));

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclusão de Cliente.", notes = "Exclui logicamente um registro de cliente na base de dados.")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        clienteService.delete(id);

        return ResponseEntity.ok().build();

    }

}
