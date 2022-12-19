package com.rogerioreis.desafio.repository;

import com.rogerioreis.desafio.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ItemPedidoRepository extends JpaRepository<Item, Long> {

}
