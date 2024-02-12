package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
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
    @Column(name = "DATA_CADASTRO", nullable = false)
    @Schema(name = "Data do cadastro do contato da pessoa.")
    private ZonedDateTime dataInicio;

    @Getter
    @Setter
    @Column(name = "DATA_FIM")
    @Schema(name = "Data fim da vigência do cadastro do contato da pessoa.")
    private ZonedDateTime dataFim;

    @Getter
    @Setter
    @Builder.Default
    @CollectionTable
    @OneToMany(mappedBy = "contato")
    @Column(name = "email")
    @Schema(name = "Lista de emails da pessoa.")
    private Set<Email> emails = new HashSet<>();

    @Getter
    @Setter
    @Builder.Default
    @CollectionTable
    @OneToMany(mappedBy = "contato")
    @Column(name = "telefone")
    @Schema(name = "Lista de telefone da pessoa.")
    private Set<Telefone> telefones = new HashSet<>();

    @Getter
    @Setter
    @JsonIgnore
    @Schema(name = "Pessoa")
    @OneToOne(optional = false)
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_PESSOA"))
    private Pessoa pessoa;

    public Contato() {
    }

    @PrePersist
    private void prePersist() {
        this.dataInicio = ZonedDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contato contato)) return false;
        return Objects.equals(getId(), contato.getId()) && Objects.equals(getDataInicio(), contato.getDataInicio()) && Objects.equals(getDataFim(), contato.getDataFim()) && Objects.equals(getEmails(), contato.getEmails()) && Objects.equals(getTelefones(), contato.getTelefones());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataInicio(), getDataFim(), getEmails(), getTelefones());
    }
}
