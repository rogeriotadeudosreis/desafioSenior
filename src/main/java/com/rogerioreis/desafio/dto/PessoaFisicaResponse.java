package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumNacionalidade;
import com.rogerioreis.desafio.enuns.EnumSexo;

import java.time.LocalDate;

public record PessoaFisicaResponse(
        Long id,
        String nome,
        String nomeSocial,
        EnumSexo sexo,
        EnumNacionalidade nacionalidade,
        LocalDate dataNascimento,
        PessoaResponse pessoaResponse) {
}
