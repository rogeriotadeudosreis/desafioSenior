package com.rogerioreis.desafio.repositories;

import com.rogerioreis.desafio.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "select cli from Cliente cli where upper(cli.nome) like upper(concat('%',:descricao,'%'))"
            + "or upper(cli.email) like upper(concat('%',:descricao,'%'))")
    Page<Cliente> findAllByNomeLikeIgnoreCaseOrEmailIgnoreCase(@Param("descricao") String descricao, @Param("pageable") Pageable pageable);

    Optional<Cliente> findClienteByEmailIgnoreCase(String email);
}
