//package com.rogerioreis.desafio.controllers;
//
//
//import com.rogerioreis.desafio.model.Product;
//import com.rogerioreis.desafio.services.ProductServiceImpl;
//import com.rogerioreis.desafio.services.ProdutoService;
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
//@RequestMapping(value = "/api/produtos")
//public class ProductController {
//
//    @Autowired
//    private ProdutoService produtoService;
//
//    @Autowired
//    private ProductServiceImpl productServiceImpl;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Transactional
//    @Operation(summary = "Cadastro de Produto.")
//    public ResponseEntity<ProductRequest> create(@RequestBody @Valid ProductRequest produtoFormDto, UriComponentsBuilder uriBuilder) {
//
//        Product produtoSalvo = this.modelMapper.map(produtoFormDto, Product.class);
//
//        produtoService.create(produtoSalvo);
//
//        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoSalvo.getId()).toUri();
//
//        ProductRequest dto = this.modelMapper.map(produtoSalvo, ProductRequest.class);
//
//        return ResponseEntity.created(uri).body(dto);
//
//    }
//
//    @GetMapping(path = "/page", produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Transactional
//    @Operation(summary = "Consulta paginada de Produto.")
//    public ResponseEntity<Page> page(
//            @RequestParam(required = false) String descricao,
//            @RequestParam(defaultValue = "0") Integer page,
//            @RequestParam(defaultValue = "20") Integer size) {
//
//        Page<Product> list = produtoService.page(descricao, PageRequest.of(page, size));
//
//        HttpStatus status = HttpStatus.OK;
//
//        return ResponseEntity.status(status).body(list);
//
//    }
//
//    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Transactional
//    @Operation(summary = "Consulta de Produto.")
//    public ResponseEntity<ProductResponse> readById(@PathVariable Long id) {
//
//        return ResponseEntity.ok(this.modelMapper.map(
//                produtoService.readById(id), ProductResponse.class));
//
//    }
//
//    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    @Transactional
//    @Operation(summary = "Atualização de Produto.")
//    public ResponseEntity<ProductRequest> update(@PathVariable Long id, @Valid @RequestBody ProductRequest produtoFormDto) {
//
//        Product produtoAtualizado = this.modelMapper.map(produtoFormDto, Product.class);
//
//        produtoAtualizado.setId(id);
//
//        ProductRequest dto = new ProductRequest(this.modelMapper
//                .map(this.produtoService.update(produtoAtualizado), ProductRequest.class));
//
//        return ResponseEntity.ok(dto);
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Exclusão de Produto.")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//
//        productServiceImpl.delete(id);
//
//        return ResponseEntity.ok().build();
//
//    }
//}
