package com.rogerioreis.desafio.model;

import com.rogerioreis.desafio.enuns.EnumTipoEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@EqualsAndHashCode
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

//    @Getter
//    @Setter
//    @Column(name = "DATA_CADASTRO")
//    @Schema(description = "Data de cadastro.")
//    private ZonedDateTime dataCadastro;
//
//    @Getter
//    @Setter
//    @Column(name = "FIM_VIGENCIA")
//    @Schema(description = "Período de vigência do cadastro.")
//    private ZonedDateTime fimVigencia;
//
//    @Getter
//    @Setter
//    @Column(name = "DATA_ATUALIZACAO")
//    @Schema(description = "Atualização do cadastro.")
//    private ZonedDateTime dataAtualizacao;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_CONTATO", nullable = false, referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_CONTATO"))
    @Schema(description = "Contato")
    private Contato contato;

//    @PrePersist
//    private void prePersist() {
//        this.dataCadastro = ZonedDateTime.now();
//        this.dataAtualizacao = ZonedDateTime.now();
//    }
}
