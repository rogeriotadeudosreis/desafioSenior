package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rogerioreis.desafio.enun.EnumSituacao;
import com.rogerioreis.desafio.enun.EnumTipoCliente;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"pessoaFisica", "pessoaJuridica", "contato"})
@Entity(name = "CLIENTE")
@Schema(name = "Cliente", description = "Cadastro de cliente.")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador da cliente na base de dados.")
    private Long id;

    @Getter
    @Setter
    @Column(name = "DATA_INICIO", nullable = false, updatable = false)
    @Schema(description = "Data de cadastro.")
    private ZonedDateTime dataInicio;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "America/Sao_Paulo")
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
    @Schema(name = "Situacao do cadastro da cliente.", allowableValues = "{ATIVO, INATIVO}")
    private EnumSituacao situacao;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_CLIENTE")
    @Schema(name = "informa o tipo de cliente.", allowableValues = "{PF, PJ}")
    private EnumTipoCliente tipoCliente;

    @Getter
    @Setter
    @Schema(name = "Contato do cliente.")
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Contato contato;

    @Getter
    @Setter
    @Schema(description = "Pessoa Física.")
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private PessoaFisica pessoaFisica;

    @Getter
    @Setter
    @Schema(description = "Pessoa Jurídica.")
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private PessoaJuridica pessoaJuridica;

    @Getter
    @Schema(name = "Pedido", description = "Armazena uma lista de pedidos de cada cliente.")
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @PrePersist
    private void prePersist() {
        this.contato = new Contato();
        this.contato.setCliente(this);
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
