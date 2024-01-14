package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "PRODUTO")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador automático sequencial do produto")
    private Long id;

    @Column(name = "NOME", length = 200, nullable = false)
    @Schema(description= "Descrição do produto")
    private String nome;

    @Column(name = "CODIGO", length = 200, nullable = false, unique = true)
    @Schema(description= "Código único do produto")
    private String codigo;

    @Column(name = "PRECO", nullable = false, scale = 2)
    @Schema(description= "Preço do produto")
    private BigDecimal preco;

    @Column(name = "DATA_INICIO", nullable = false, updatable = false)
    @Schema(description= "Data de cadastro do produto")
    private ZonedDateTime dataInicio;

    @Column(name = "DATA_FIM")
    @Schema(description= "Data para desativar o produto")
    private ZonedDateTime dataFim;

    @Column(name = "DATA_ATUALIZACAO")
    @Schema(description= "Data de atualização do produto")
    private ZonedDateTime dataAtualizacao;

    @Column(name = "TIPO_PRODUTO", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(description= "Identifica o tipo do produto, se produto ou serviço")
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
        return getPreco().compareTo(BigDecimal.ZERO) > 0 && (getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0);
    }
}
