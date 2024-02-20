package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rogerioreis.desafio.enuns.EnumTipoEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ENDERECO")
@Schema(description = "Armazena um endereço de cliente pessoa física ou jurídica.")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ESTADO")
    @Schema(description = "informa o estado")
    private String estado;

    @Schema(description = "Informa a cidade")
    @Column(name = "LOCALIDADE")
    private String localidade;

    @Schema(description = "Informa o CEP")
    @Column(name = "CEP")
    private String cep;

    @Schema(description = "Informa o bairro")
    @Column(name = "BAIRRO")
    private String bairro;

    @Schema(description = "Informa o logradouro")
    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Schema(description = "Informa o número")
    @Column(name = "NUMERO")
    private String numero;

    @Schema(description = "Informa o complemento do endereço se houver")
    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Schema(description = "Informa o tipo de endereço (Residencial,comercial, cobranca, outro)")
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_ENDERECO")
    private EnumTipoEndereco tipoEndereco;

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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ID_CONTATO", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_CONTATO"))
    private Contato contato;

    @PrePersist
    public void prePersist() {
        this.dataInicio = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = ZonedDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco endereco)) return false;
        return getId().equals(endereco.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
