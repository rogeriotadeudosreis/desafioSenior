package com.rogerioreis.desafio.repository;

import com.rogerioreis.desafio.model.ItemPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
