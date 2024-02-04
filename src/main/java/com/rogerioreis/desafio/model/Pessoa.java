package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rogerioreis.desafio.enuns.EnumSituacao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"pessoaFisica","pessoaJuridica","contato"})
@Entity(name = "PESSOA")
@Schema(name = "Pessoa", description = "Cadastro de pessoa.")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador da pessoa na base de dados.")
    private Long id;

    @Getter
    @Setter
    @JsonIgnore
    @Schema(description = "Pessoa Física.")
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private PessoaFisica pessoaFisica;

    @Getter
    @Setter
    @JsonIgnore
    @Schema(description = "Pessoa Jurídica.")
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private PessoaJuridica pessoaJuridica;

    @Getter
    @Setter
    @Column(name = "DATA_CADASTRO", nullable = false, updatable = false)
    @Schema(description = "Data de cadastro.")
    private ZonedDateTime dataCadastro;

    @Getter
    @Setter
    @Column(name = "FIM_VIGENCIA")
    @Schema(description = "Período de vigência do cadastro.")
    private ZonedDateTime fimVigencia;

    @Getter
    @Setter
    @Column(name = "DATA_ATUALIZACAO")
    @Schema(name = "Data de atualização de cadastro.")
    private ZonedDateTime dataAtualizacao;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO")
    @Schema(name = "Situacao do cadastro da pessoa.", allowableValues = "{ATIVO, INATIVO}")
    private EnumSituacao situacao;

    @Getter
    @Setter
    @JsonIgnore
    @Schema(name = "Contato da pessoa.")
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Contato contato;

    @PrePersist
    private void prePersist() {
        this.contato = new Contato();
        this.contato.setPessoa(this);
        this.dataCadastro = ZonedDateTime.now();
        this.situacao = EnumSituacao.ATIVO;
    }

    @PreUpdate
    private void updatePersist() {
        this.dataAtualizacao = ZonedDateTime.now();
    }
}
