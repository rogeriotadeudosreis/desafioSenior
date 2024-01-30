package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.rogerioreis.desafio.enuns.EnumCategoria;
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
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador automático sequencial do produto")
    private Long id;

    @Column(name = "NOME", length = 200, nullable = false)
    @Schema(description = "Nome do produto")
    private String nome;

    @Column(name = "DESCRICAO", length = 200, nullable = false)
    @Schema(description = "Descrição do produto")
    private String descricao;

    @Column(name = "CODIGO", length = 200, nullable = false, unique = true)
    @Schema(description = "Código único do produto")
    private String codigo;

    @Column(name = "PRECO_CUSTO", nullable = false, scale = 2)
    @Schema(description = "Preço de custo do produto")
    private BigDecimal precoCusto;

    @Column(name = "PRECO_VENDA", nullable = false, scale = 2)
    @Schema(description = "Preço de venda do produto")
    private BigDecimal precoVenda;

    @Column(name = "INICIO_VIGENCIA", nullable = false, updatable = false)
    @Schema(description = "Data de cadastro do produto")
    private ZonedDateTime inicioVigencia;

    @Column(name = "fIM_VIGENCIA")
    @Schema(description = "Data para desativar o produto")
    private ZonedDateTime fimVigencia;

    @Column(name = "ATUALIZACAO")
    @Schema(description = "Data de atualização do produto")
    private ZonedDateTime atualizacao;

    @Column(name = "TIPO", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(description = "Identifica o tipo do produto, se produto ou serviço")
    private EnumTipoProduto tipo;

    @Column(name = "estoque")
    @Schema(description = "Informa a quantidade de itens deste produto existe no estoque da empresa.")
    private Integer estoque;

    @Column(name = "CATEGORIA")
    @Enumerated(EnumType.STRING)
    @Schema(description = "Informa a categoria do produto.")
    private EnumCategoria categoria;

    @PrePersist
    private void init() {
        this.inicioVigencia = ZonedDateTime.now();
    }

    @PreUpdate
    private void update() {
        this.atualizacao = ZonedDateTime.now();
    }

    @JsonGetter
    public boolean isAtivo() {
        return getPrecoCusto().compareTo(BigDecimal.ZERO) > 0 && (getFimVigencia() == null || getFimVigencia().compareTo(ZonedDateTime.now()) > 0);
    }
}
