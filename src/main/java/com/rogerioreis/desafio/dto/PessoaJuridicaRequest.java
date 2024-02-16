package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.model.Pessoa;
import com.rogerioreis.desafio.model.Telefone;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public record PessoaJuridicaRequest(
        String razaoSocial,
        String nomeFantasia,
        String cnpj,
        String inscricaoEstadual,
        String inscricaoMunicipal,
        Pessoa pessoa,
        ZonedDateTime dataCadastro,
        ZonedDateTime dataAtualizacao,
        List<Email> emails,
        List<Telefone> telefones
) {
}
