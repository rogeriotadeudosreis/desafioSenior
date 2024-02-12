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
@EqualsAndHashCode(exclude = {"pessoaFisica", "pessoaJuridica", "contato"})
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
    @Column(name = "DATA_INICIO", nullable = false, updatable = false)
    @Schema(description = "Data de cadastro.")
    private ZonedDateTime dataInicio;

    @Getter
    @Setter
    @Column(name = "DATA_FIM")
    @Schema(description = "Período de vigência do cadastro.")
    private ZonedDateTime dataFim;

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
    @Schema(name = "Contato da pessoa.")
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Contato contato;

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

    @PrePersist
    private void prePersist() {
        this.contato = new Contato();
        this.contato.setPessoa(this);
        this.dataInicio = ZonedDateTime.now();
        this.situacao = EnumSituacao.ATIVO;
    }

    @PreUpdate
    private void updatePersist() {
        this.dataAtualizacao = ZonedDateTime.now();
        if (this.dataFim != null) {
            this.situacao = EnumSituacao.INATIVO;
        }
    }

}
