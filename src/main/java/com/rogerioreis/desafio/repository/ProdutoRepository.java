package com.rogerioreis.desafio.repository;

import com.rogerioreis.desafio.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
