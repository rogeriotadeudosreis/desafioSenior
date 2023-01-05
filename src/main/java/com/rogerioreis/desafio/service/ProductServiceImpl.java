package com.rogerioreis.desafio.service;

import com.rogerioreis.desafio.interfaces.GenericService;
import com.rogerioreis.desafio.model.Produto;
import com.rogerioreis.desafio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements GenericService<Produto, Long> {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public JpaRepository<Produto, Long> getRepository() {
        return produtoRepository;
    }

    @Override
    public Page<Produto> read(String descricao, Pageable pageable) {
        return null;
    }
}
