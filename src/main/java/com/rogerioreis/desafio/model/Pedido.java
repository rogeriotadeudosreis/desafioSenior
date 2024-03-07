package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.rogerioreis.desafio.enun.EnumSituacaoPedido;
import com.rogerioreis.desafio.enun.EnumTipoProduto;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMERO", unique = true)
    private String numero;

    @Column(name = "DATA_INICIO")
    private ZonedDateTime dataInicio;

    @Column(name = "DATA_FIM")
    private ZonedDateTime dataFim;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", foreignKey = @ForeignKey(name = "FK_CLIENTE_ID"))
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<Item> itens = new ArrayList<>();

    @Column(name = "SUBTOTAL", precision = 9, scale = 2)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal subTotal;

    @Column(name = "DESCONTO", precision = 9, scale = 2)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal desconto;

    @Column(name = "TOTAL", scale = 2)
    private BigDecimal total;

    @Column(name = "SITUACAO", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private EnumSituacaoPedido situacao = EnumSituacaoPedido.ABERTO;

    private BigDecimal calcularSubTotal() {
        BigDecimal soma = BigDecimal.ZERO;
        for (Item item : itens) {
            soma = soma.add(item.getSubTotal());
        }
        return soma;
    }

    private BigDecimal calcularDescontoProduto() {
        BigDecimal soma = BigDecimal.ZERO;

        for (Item item : itens) {
            if (item.getProduto().getTipo().equals(EnumTipoProduto.PRODUTO)
                    && situacao.equals(EnumSituacaoPedido.ABERTO)) {
                soma = soma.add(item.getSubTotal());
            }
        }
        BigDecimal desconto = this.desconto.multiply(soma).divide(new BigDecimal("100"));

        if (desconto.compareTo(soma) == 1) {
            throw new RegraNegocioException("O valor do desconto não pode ser maior do o total de produtos.");
        }
        this.setSituacao(EnumSituacaoPedido.FECHADO);
        return desconto;
    }

    public String getNumero() {
        String prefixo = "PED Nº: " + getId();
        return prefixo;
    }

    @JsonGetter
    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }

    @PrePersist
    private void prePersist() {
        this.dataInicio = ZonedDateTime.now();
        this.subTotal = calcularSubTotal();
        this.desconto = calcularDescontoProduto();
        this.total = getSubTotal().subtract(this.desconto);
        this.situacao = EnumSituacaoPedido.ABERTO;
    }

    @PreUpdate
    private void preUpdate() {
        this.subTotal = calcularSubTotal();
        this.desconto = calcularDescontoProduto();
        this.total = getSubTotal().subtract(this.desconto);
    }

}
