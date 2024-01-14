package com.rogerioreis.desafio.repositories;

import com.rogerioreis.desafio.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select item from Item item where upper(item.produto.nome) like upper(concat('%',:descricao,'%'))"
            + "or upper(item.produto.codigo) like upper(concat('%',:descricao,'%'))")
    Page<Item> findAllByProdutoLikeIgnoreCase(@Param("descricao") String descricao,
                                              @Param("pageable") Pageable pageable);
}
