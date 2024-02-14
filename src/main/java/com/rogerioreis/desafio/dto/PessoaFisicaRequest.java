package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumNacionalidade;
import com.rogerioreis.desafio.enuns.EnumSexo;
import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.model.Pessoa;
import com.rogerioreis.desafio.model.Telefone;

import java.time.LocalDate;
import java.util.Set;

public record PessoaFisicaRequest(

        Long id,
        String nome,
        String nomeSocial,
        String cpf,
        String rg,
        String passaporte,
        EnumSexo sexo,
        EnumNacionalidade nacionalidade,
        LocalDate dataNascimento,
        Pessoa pessoa,
        Set<Email> emails,
        Set<Telefone> telefones
) {
}
