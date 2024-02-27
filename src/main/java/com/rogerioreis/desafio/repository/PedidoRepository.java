package com.rogerioreis.desafio.repository;

import com.rogerioreis.desafio.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "select ped from Pedido ped where upper(ped.cliente.pessoaFisica.nome) like upper(concat('%',:descricao,'%'))"
            + "or upper(ped.cliente.pessoaFisica.cpf) like upper(concat('%',:descricao,'%'))"
            + "or upper(ped.numero) like upper(concat('%',:descricao,'%') ) ")
    Page<Pedido> findAllByClienteLikeIgnoreCase(@Param("descricao") String descricao,
                                                @Param("pageable") Pageable pageable);

    @Query(value = "SELECT MAX(p.id) FROM Pedido p")
    Long findTopByOrderByIdDesc(); // Encontre o último ID ordenado descendentemente

}
