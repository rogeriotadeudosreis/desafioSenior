package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.rogerioreis.desafio.enuns.EnumSituacaoPedido;
import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.Digits;
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
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUM_PEDIDO", updatable = false)
    private String numeroPedido;

    @Column(name = "DATA_INICIO", nullable = false, updatable = false)
    private ZonedDateTime dataInicio;

    @Column(name = "DATA_FIM")
    private ZonedDateTime dataFim;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "CLIENTE_FK"))
    private Cliente cliente;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "item_pedido",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> itens = new ArrayList<>();

    @Column(name = "SUBTOTAL_PEDIDO", scale = 2)
    private BigDecimal subTotalPedido;

    @Column(name = "DESCONTO", scale = 2)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal desconto;

    @Column(name = "TOTAL_PEDIDO", scale =2)
    private BigDecimal totalPedido;

    @Column(name = "SITUACAO", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private EnumSituacaoPedido situacao = EnumSituacaoPedido.ABERTO;

    public Order(Cliente cliente) {
        this.cliente = cliente;
    }

    public Order(Long id, String numeroPedido, Cliente cliente, List<Item> itens, BigDecimal desconto, EnumSituacaoPedido situacao) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.itens = itens;
        this.desconto = desconto;
        this.situacao = situacao;
    }

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
//        BigDecimal desconto = this.desconto * soma / 100;

        if (desconto.compareTo(soma) == 1) {
            throw new RegraNegocioException("O valor do desconto não pode ser maior do o total de produtos.");
        }
        this.setSituacao(EnumSituacaoPedido.FECHADO);
        return desconto;
    }

    public String getNumeroPedido() {
        String prefixo = "PED Nº: " + getId();
        return prefixo;
    }

    @JsonGetter
    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }

    @PrePersist
    private void init() {
        this.dataInicio = ZonedDateTime.now();
        this.subTotalPedido = calcularSubTotal();
        this.desconto = calcularDescontoProduto();
        this.totalPedido = getSubTotalPedido().subtract(this.desconto);
    }

    @PreUpdate
    private void update(){
        this.subTotalPedido = calcularSubTotal();
        this.desconto = calcularDescontoProduto();
        this.totalPedido = getSubTotalPedido().subtract(this.desconto);
    }

}
