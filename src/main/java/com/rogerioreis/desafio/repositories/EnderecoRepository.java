package com.rogerioreis.desafio.repositories;

import com.rogerioreis.desafio.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    List<Endereco> findAllByClienteId(Long idCliente);

}
