package com.rogerioreis.desafio.repository;

import com.rogerioreis.desafio.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

    List<Telefone> findAllByContatoId(Long idContato);
}
