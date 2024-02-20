package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rogerioreis.desafio.enuns.EnumTipoEmail;
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
@Entity(name = "EMAIL")
@Schema(name = "Email", description = "Cadastro de email.")
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador da entidade email.")
    private Long id;

    @Getter
    @Setter
    @jakarta.validation.constraints.Email(message = "Email inválido")
    @Column(name = "EMAIL", length = 100)
    @Schema(description = "Descrição do e-mail.")
    private String email;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO", length = 15)
    @Schema(description = "Tipo de e-mail.")
    private EnumTipoEmail tipo;

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
    @Schema(description = "Contato")
    private Contato contato;

    @PrePersist
    private void prePersist() {
        this.dataInicio = ZonedDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        this.dataAtualizacao = ZonedDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email email1)) return false;
        return Objects.equals(getId(), email1.getId()) && Objects.equals(getEmail(), email1.getEmail()) && getTipo() == email1.getTipo() && Objects.equals(getDataInicio(), email1.getDataInicio()) && Objects.equals(getDataFim(), email1.getDataFim()) && Objects.equals(getDataAtualizacao(), email1.getDataAtualizacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getTipo(), getDataInicio(), getDataFim(), getDataAtualizacao());
    }
}
