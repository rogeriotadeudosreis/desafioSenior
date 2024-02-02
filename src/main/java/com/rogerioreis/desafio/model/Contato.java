package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "CONTATO")
@Schema(name = "Contato", description = "Cadastro de um contato.")
public class Contato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador do contato da pessoa.")
    private Long id;

    @Getter
    @Setter
    @Schema(name = "Pessoa")
    @OneToOne(optional = false)
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_PESSOA"))
    private Pessoa pessoa;

    @Getter
    @Setter
    @Column(name = "DATA_CADASTRO", nullable = false)
    @Schema(name = "Data do cadastro do contato da pessoa.")
    private ZonedDateTime dataCadastro;

    @Getter
    @Setter
    @Column(name = "FIM_VIGENCIA")
    @Schema(name = "Data fim da vigência do cadastro do contato da pessoa.")
    private ZonedDateTime fimVigencia;
//
    @Getter
    @JsonIgnore
    @Builder.Default
    @CollectionTable
    @OneToMany(mappedBy = "contato")
    @Column(name = "email")
    @Schema(name = "Lista de emails da pessoa.")
    private Set<Email> emails = new HashSet<>();

    @Getter
    @JsonIgnore
    @Builder.Default
    @CollectionTable
    @OneToMany(mappedBy = "contato")
    @Column(name = "telefone")
    @Schema(name = "Lista de telefone da pessoa.")
    private Set<Telefone> telefones = new HashSet<>();

    public Contato() {
    }

    @PrePersist
    private void prePersist() {
        this.dataCadastro = ZonedDateTime.now();
    }


}
