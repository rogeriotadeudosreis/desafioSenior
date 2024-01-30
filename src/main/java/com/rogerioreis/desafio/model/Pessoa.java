package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogerioreis.desafio.enuns.EnumSituacao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PESSOA")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador da pessoa na base de dados.")
    private Long id;

    @JsonIgnore
    @JsonIgnoreProperties({"pessoa"})
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PESSOA_FISICA", foreignKey = @ForeignKey(name = "FK_PESSOA_FISICA"))
    private PessoaFisica pessoaFisica;

    private PessoaJuridica pessoaJuridica;

    private ZonedDateTime inicioVigencia;

    private ZonedDateTime fimVigencia;

    private ZonedDateTime Atualizacao;

    private EnumSituacao situacao;

    private Contato contato;
}
