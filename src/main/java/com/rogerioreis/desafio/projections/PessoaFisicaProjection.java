package com.rogerioreis.desafio.projections;

import com.rogerioreis.desafio.enuns.EnumNacionalidade;
import com.rogerioreis.desafio.enuns.EnumSexo;
import com.rogerioreis.desafio.model.Cliente;

import java.time.LocalDate;

public interface PessoaFisicaProjection {

    Long getId();

    String getNome();

    String getNomeSocial();

    String getCpf();

    String getRg();

    String getPassaporte();

    EnumSexo getSexo();

    EnumNacionalidade getNacionalidade();

    LocalDate getDataNascimento();

    Cliente getCliente();
}
