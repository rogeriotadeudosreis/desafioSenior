package com.rogerioreis.desafio.repository;

import com.rogerioreis.desafio.model.PessoaFisica;
import com.rogerioreis.desafio.projection.PessoaFisicaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

    Optional<PessoaFisicaProjection> findByCpf(String cpf);

    @Query(value = "SELECT DISTINCT pf.id as id, pf.nome as nome, pf.nomeSocial as nomeSocial," +
            " pf.cpf as cpf, pf.rg as rg, pf.passaporte as passaporte, pf.sexo as sexo," +
            " pf.nacionalidade as nacionalidade, pf.dataNascimento as dataNascimento, " +
            " pf.cliente as cliente, pf.cliente.contato as contato from PESSOA_FISICA pf " +
            " where upper(pf.nome) like %:name% ")
    List<PessoaFisicaProjection> findAllByName(@RequestParam("name") String name);

    @Query(value = " SELECT DISTINCT pf.id as id, pf.nome as nome, pf.nomeSocial as nomeSocial," +
            " pf.cpf as cpf, pf.rg as rg, pf.passaporte as passaporte, pf.sexo as sexo," +
            " pf.nacionalidade as nacionalidade, pf.dataNascimento as dataNascimento, " +
            " pf.cliente as cliente, pf.cliente.contato as contato from PESSOA_FISICA pf " +
            " inner join EMAIL e on e.contato.id = pf.cliente.contato.id " +
            " where e.email = :emailCpf or pf.cpf = :emailCpf ")
    Optional<PessoaFisicaProjection> findPessoaFisicaByEmailOrCpf(@RequestParam("emailCpf") String emailCpf);

}


