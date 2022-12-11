package com.rogerioreis.desafio.repository;

import com.rogerioreis.desafio.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value = "select prod from PRODUTO prod where upper(prod.nome) like upper(concat('%',:descricao,'%'))"
            + "or upper(prod.codigo) like upper(concat('%',:descricao,'%'))")
    Page<Produto> findAllByNomeLikeIgnoreCaseOrCodigoIgnoreCase(@Param("descricao") String descricao, @Param("pageable") Pageable pageable);
}
