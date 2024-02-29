package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rogerioreis.desafio.enun.EnumTipoTelefone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TELEFONE")
@Schema(name = "Telefone", description = "Telefone de uma pessoa.")
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador da entidade")
    private Long id;

    @Getter
    @Setter
    @Column(name = "TELEFONE", length = 12)
    @Schema(description = "Informa o telefone de contato.")
    private String telefone;

    @Getter
    @Setter
    @Column(name = "DDD", length = 3)
    @Schema(description = "Informa o DDD")
    private String ddd;

    @Getter
    @Setter
    @Column(name = "DDI", length = 3)
    @Schema(description = "Informa o DDI.")
    private String ddi;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", length = 20)
    @Schema(description = "Informa o tipo de telefone.")
    private EnumTipoTelefone tipo;

    @Getter
    @Setter
    @Column(name = "DATA_INICIO")
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
    @Schema(description = "Atualização do cadastro.")
    private ZonedDateTime dataAtualizacao;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_CONTATO", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_CONTATO"))
    @Schema(description = "contato")
    private Contato contato;

    @PrePersist
    private void prePersist() {
        this.dataInicio = ZonedDateTime.now();
    }

    @PreUpdate
    private void updatePersist() {
        this.dataAtualizacao = ZonedDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Telefone telefone1)) return false;
        return Objects.equals(getId(), telefone1.getId()) && Objects.equals(getTelefone(), telefone1.getTelefone()) && Objects.equals(getDdd(), telefone1.getDdd()) && Objects.equals(getDdi(), telefone1.getDdi()) && getTipo() == telefone1.getTipo() && Objects.equals(getDataInicio(), telefone1.getDataInicio()) && Objects.equals(getDataFim(), telefone1.getDataFim()) && Objects.equals(getDataAtualizacao(), telefone1.getDataAtualizacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTelefone(), getDdd(), getDdi(), getTipo(), getDataInicio(), getDataFim(), getDataAtualizacao());
    }
}
