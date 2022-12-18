package com.rogerioreis.desafio.controller;


import com.rogerioreis.desafio.dto.ProdutoConsultaDto;
import com.rogerioreis.desafio.dto.ProdutoFormDto;
import com.rogerioreis.desafio.model.Produto;
import com.rogerioreis.desafio.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Cadastro de Produto.", notes = "Armazena um registro de produto na base de dados.")
    public ResponseEntity<ProdutoFormDto> create(@RequestBody @Valid ProdutoFormDto produtoFormDto, UriComponentsBuilder uriBuilder) {

        Produto produtoSalvo = this.modelMapper.map(produtoFormDto, Produto.class);

        produtoService.create(produtoSalvo);

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoSalvo.getId()).toUri();

        ProdutoFormDto dto = this.modelMapper.map(produtoSalvo, ProdutoFormDto.class);

        return ResponseEntity.created(uri).body(dto);

    }

    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Consulta paginada de Produto.", notes = "Consulta de produto na base de dados com paginação.")
    public ResponseEntity<Page> page(
            @RequestParam(required = false) String descricao,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size) {

        Page<Produto> list = produtoService.page(descricao, PageRequest.of(page, size));

        HttpStatus status = HttpStatus.OK;

        return ResponseEntity.status(status).body(list);

    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Consulta de Produto.", notes = "Consulta um registro de produto na base de dados pelo seu identificador.")
    public ResponseEntity<ProdutoConsultaDto> readById(@PathVariable Long id) {

        return ResponseEntity.ok(this.modelMapper.map(
                produtoService.readById(id), ProdutoConsultaDto.class));

    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    @ApiOperation(value = "Atualização de Produto.", notes = "Atualiza um registro de produto na base de dados.")
    public ResponseEntity<ProdutoFormDto> update(@PathVariable Long id, @Valid @RequestBody ProdutoFormDto produtoFormDto) {

        Produto produto = this.modelMapper.map(produtoFormDto, Produto.class);

        Produto produtoAtualizado =  this.produtoService.update(id, produto);

        ProdutoFormDto dto = this.modelMapper.map(produtoAtualizado, ProdutoFormDto.class);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclusão de Produto.", notes = "Exclui logicamente um registro de produto na base de dados.")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        produtoService.delete(id);

        return ResponseEntity.ok().build();

    }
}
