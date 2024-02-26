package com.rogerioreis.desafio.repositories;

import com.rogerioreis.desafio.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findAllByContatoId(Long idContato);

    Optional<Email> findByEmail(@Param("email") String email);
}
