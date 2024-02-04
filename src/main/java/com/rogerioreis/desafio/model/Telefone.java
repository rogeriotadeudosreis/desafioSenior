package com.rogerioreis.desafio.model;

import com.rogerioreis.desafio.enuns.EnumTipoTelefone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@EqualsAndHashCode
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
    private EnumTipoTelefone tipoTelefone;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_CONTATO", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_CONTATO"))
    @Schema(description = "contato")
    private Contato contato;

    @Getter
    @Setter
    @Column(name = "DATA_CADASTRO")
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
    @Schema(description = "Atualização do cadastro.")
    private ZonedDateTime dataAtualizacao;

    @PrePersist
    private void prePersist() {
        this.dataCadastro = ZonedDateTime.now();
    }


}
