package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "PRODUTO")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Identificador automático sequencial do produto")
    private Long id;

    @Column(name = "NOME", length = 200, nullable = false)
    @ApiModelProperty(value = "Descrição do produto")
    private String nome;

    @Column(name = "CODIGO", length = 200, nullable = false, unique = true)
    @ApiModelProperty(value = "Código único do produto")
    private String codigo;

    @Column(name = "PRECO", nullable = false)
    @ApiModelProperty(value = "Preço do produto")
    private double preco;

    @Column(name = "DATA_INICIO", nullable = false, updatable = false)
    @ApiModelProperty(value = "Data de cadastro do produto")
    private ZonedDateTime dataInicio;

    @Column(name = "DATA_FIM")
    @ApiModelProperty(value = "Data para desativar o produto")
    private ZonedDateTime dataFim;

    @Column(name = "DATA_ATUALIZACAO")
    @ApiModelProperty(value = "Data de atualização do produto")
    private ZonedDateTime dataAtualizacao;

    @Column(name = "TIPO_PRODUTO", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Identifica o tipo do produto, se produto ou serviço")
    private EnumTipoProduto tipoProduto;

    @PrePersist
    private void init() {
        this.dataInicio = ZonedDateTime.now();
    }

    @PreUpdate
    private void update() {
        this.dataAtualizacao = ZonedDateTime.now();
    }

    @JsonGetter
    public boolean isAtivo() {
        return getPreco() > 0 && (getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0);
    }

}
