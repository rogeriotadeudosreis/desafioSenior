package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumNacionalidade;
import com.rogerioreis.desafio.enuns.EnumSexo;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.model.Endereco;
import com.rogerioreis.desafio.model.Telefone;

import java.time.LocalDate;
import java.util.List;

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
        Cliente cliente,
        List<Email> emails,
        List<Telefone> telefones,
        List<Endereco> enderecos

) {
}
