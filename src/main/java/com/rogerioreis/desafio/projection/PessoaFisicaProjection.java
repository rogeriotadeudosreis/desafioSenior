package com.rogerioreis.desafio.projection;

import com.rogerioreis.desafio.enun.EnumNacionalidade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface PessoaFisicaProjection {

    Long getId();

    String getNome();

    @Value(value = "#{target.nomeSocial != null ? target.nomeSocial : '' }")
    String getNomeSocial();

    String getCpf();

    @Value(value = "#{target.rg != null ? target.rg : '' }")
    String getRg();

    @Value(value = "#{target.passaporte != null ? target.passaporte : '' }")
    String getPassaporte();

    String getSexo();

    EnumNacionalidade getNacionalidade();

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate getDataNascimento();

    @Value(value = "#{target.cliente != null ? target.cliente.dataFim == null : true }")
    boolean isAtivo();

    @Value(value = "#{target.cliente.contato != null ? target.cliente.contato.emails : '' }")
    List<EmailProjetcion> getEmail();

    @Value(value = "#{target.cliente.contato != null ? target.cliente.contato.telefones: '' }")
    List<TelefoneProjection> getTelefone();

    @Value(value = "#{target.cliente.contato != null ? target.cliente.contato.enderecos : '' }")
    List<EnderecoProjection> getEndereco();
}
