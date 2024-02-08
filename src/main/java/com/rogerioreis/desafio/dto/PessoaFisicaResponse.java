package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumNacionalidade;
import com.rogerioreis.desafio.enuns.EnumSexo;
import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.model.Telefone;

import java.util.Set;

public record PessoaFisicaResponse(

        String nome,
        String nomeSocial,
        EnumSexo sexo,
        EnumNacionalidade nacionalidade,
        PessoaResponse pessoaResponse,
        Set<Email> emails,
        Set<Telefone> telefones
) {
}
