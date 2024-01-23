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
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMERO", updatable = false, unique = true)
    private String numero;

    @Column(name = "INICIO_VIGENCIA", updatable = false)
    private ZonedDateTime inicioVigencia;

    @Column(name = "FIM_VIGENCIA")
    private ZonedDateTime fimVigencia;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CLIENTE_ID"))
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<Item> itens = new ArrayList<>();

    @Column(name = "SUBTOTAL", scale = 2)
    private BigDecimal subTotal;

    @Column(name = "DESCONTO", scale = 2)
    @Digits(integer = 9, fraction = 2)
    private BigDecimal desconto;

    @Column(name = "TOTAL", scale =2)
    private BigDecimal total;

    @Column(name = "SITUACAO", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private EnumSituacaoPedido situacao = EnumSituacaoPedido.ABERTO;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido(Long id, String numeroPedido, Cliente cliente, List<Item> itens, BigDecimal desconto, EnumSituacaoPedido situacao) {
        this.id = id;
        this.numero = numeroPedido;
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
        return getFimVigencia() == null || getFimVigencia().compareTo(ZonedDateTime.now()) > 0;
    }

    @PrePersist
    private void init() {
        this.inicioVigencia = ZonedDateTime.now();
        this.subTotal = calcularSubTotal();
        this.desconto = calcularDescontoProduto();
        this.total = getSubTotal().subtract(this.desconto);
    }

    @PreUpdate
    private void update(){
        this.subTotal = calcularSubTotal();
        this.desconto = calcularDescontoProduto();
        this.total = getSubTotal().subtract(this.desconto);
    }

}
