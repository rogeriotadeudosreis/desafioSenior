package com.rogerioreis.desafio.repository;

import com.rogerioreis.desafio.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT cli FROM Cliente cli WHERE cli.nome LIKE UPPER(CONCAT('%',:descricao,'%'))")
    Page<Cliente> page(@Param("descricao") String descricao, @Param("pageable") Pageable pageable);
}
