package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.interfaces.GenericService;
import com.rogerioreis.desafio.model.Product;
import com.rogerioreis.desafio.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements GenericService<Product, Long> {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public JpaRepository<Product, Long> getRepository() {
        return produtoRepository;
    }

    @Override
    public Page<Product> read(String descricao, Pageable pageable) {
        return null;
    }
}
