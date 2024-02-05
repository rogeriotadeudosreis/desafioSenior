package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumNacionalidade;
import com.rogerioreis.desafio.enuns.EnumSexo;
import com.rogerioreis.desafio.model.Contato;
import com.rogerioreis.desafio.model.Pessoa;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PessoaFisicaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    private String nomeSocial;

    private String cpf;

    private String rg;

    private String passaporte;

    private EnumSexo sexo;

    private EnumNacionalidade nacionalidade;

    private Pessoa pessoa;

    private Contato contato;


}
