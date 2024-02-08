package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumNacionalidade;
import com.rogerioreis.desafio.enuns.EnumSexo;
import com.rogerioreis.desafio.enuns.EnumSituacao;

public record PessoaFisicaResponse(

        String nome,
        String nomeSocial,
        EnumSexo sexo,
        EnumNacionalidade nacionalidade,
        EnumSituacao ativo
) {
}
