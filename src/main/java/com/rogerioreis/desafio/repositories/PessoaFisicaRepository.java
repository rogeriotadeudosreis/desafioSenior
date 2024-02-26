package com.rogerioreis.desafio.repositories;

import com.rogerioreis.desafio.model.PessoaFisica;
import com.rogerioreis.desafio.projections.PessoaFisicaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

    Optional<PessoaFisica> findByCpf(String cpf);

    Optional<PessoaFisica> findByNome(String nome);

    @Query(value = " SELECT pf.id as id, pf.nome as nome, pf.nomeSocial as nomeSocial," +
            " pf.cpf as cpf, pf.rg as rg, pf.passaporte as passaporte, pf.sexo as sexo," +
            " pf.nacionalidade as nacionalidade, pf.dataNascimento as dataNascimento, " +
            " pf.cliente as cliente from PESSOA_FISICA pf " +
            " inner join CLIENTE c on pf.cliente.id = c.id " +
            " inner join CONTATO con on c.contato.id = con.id " +
            " inner join EMAIL e on e.contato.id = con.id " +
            " and e.tipo = 'PRINCIPAL' " +
            " where e.email = :email  ")
    Optional<PessoaFisicaProjection> findPessoaFisicaByEmail(@RequestParam("email") String email);

}


