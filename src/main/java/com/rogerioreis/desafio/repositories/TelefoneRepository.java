package com.rogerioreis.desafio.repositories;

import com.rogerioreis.desafio.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    Set<Telefone> findAllByContatoId(Long idContato);
}
