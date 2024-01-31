package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rogerioreis.desafio.enuns.EnumSituacao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnoreProperties({"pessoa"})
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PESSOA_FISICA", foreignKey = @ForeignKey(name = "FK_PESSOA_FISICA"))
    @Schema(description = "Pessoa física")
    private PessoaFisica pessoaFisica;

    @Getter
    @Setter
    @JsonIgnore
    @JsonIgnoreProperties({"pessoa"})
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PESSOA_JURIDICA", foreignKey = @ForeignKey(name = "FK_PESSOA_JURIDICA"))
    @Schema(description = "Pessoa jurídica")
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
    @Column(name = "CONTATO")
    @Schema(name = "Contato da pessoa.")
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_CONTATO", referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_CONTATO"))
    private Contato contato;

    @PrePersist
    private void prePersist() {
        this.contato = new Contato();
        this.dataCadastro = ZonedDateTime.now();
        this.dataAtualizacao = ZonedDateTime.now();
        this.situacao = EnumSituacao.ATIVO;
    }

    @PreUpdate
    private void updatePersist() {
        this.dataAtualizacao = ZonedDateTime.now();
    }
}
