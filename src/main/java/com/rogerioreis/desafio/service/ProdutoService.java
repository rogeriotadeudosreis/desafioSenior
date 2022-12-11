package com.rogerioreis.desafio.service;

import com.rogerioreis.desafio.model.Produto;
import com.rogerioreis.desafio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Produto create(Produto produto) {

        Produto produtoSalvo = this.produtoRepository.save(produto);

        return produtoSalvo;

    }

    public Produto readById(Long id) {

        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        return produto;

    }

    public Page<Produto> page(String descricao, Pageable pageable) {

        String desc = descricao != null ? descricao : "";

        return produtoRepository.findAllByNomeLikeIgnoreCaseOrCodigoIgnoreCase(desc, pageable);
    }

    public Produto update(Long id, Produto produtoForm) {

        Produto produto = this.readById(id);

        produtoForm.setId(produto.getId());

        return produtoRepository.save(produtoForm);

    }

    public void delete(Long id) {

        Produto produto = readById(id);

        produto.setDataFim(ZonedDateTime.now());

        this.produtoRepository.save(produto);

    }
}
