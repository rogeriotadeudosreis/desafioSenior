package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.model.Endereco;
import com.rogerioreis.desafio.model.Telefone;

import java.time.ZonedDateTime;
import java.util.List;

public record PessoaJuridicaRequest(
        Long id,
        String razaoSocial,
        String nomeFantasia,
        String cnpj,
        String inscricaoEstadual,
        String inscricaoMunicipal,
        Cliente cliente,
        ZonedDateTime dataCadastro,
        ZonedDateTime dataAtualizacao,
        List<Email> emails,
        List<Telefone> telefones,
        List<Endereco> enderecos
) {
}
