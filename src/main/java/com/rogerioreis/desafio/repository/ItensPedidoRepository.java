package com.rogerioreis.desafio.repository;

import com.rogerioreis.desafio.model.ItensPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Long> {
}
