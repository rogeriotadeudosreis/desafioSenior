package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.rogerioreis.desafio.enuns.EnumSituacaoPedido;
import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
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

    @Column(name = "SUBTOTAL_PEDIDO")
    private double subTotalPedido;

    @Column(name = "DESCONTO")
    @Digits(integer = 9, fraction = 2)
    private double desconto;

    @Column(name = "TOTAL_PEDIDO")
    private double totalPedido;

    @Column(name = "SITUACAO", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private EnumSituacaoPedido situacao = EnumSituacaoPedido.ABERTO;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido(Long id, String numeroPedido, Cliente cliente, List<Item> itens, double desconto, EnumSituacaoPedido situacao) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.itens = itens;
        this.desconto = desconto;
        this.situacao = situacao;
    }

    private double calcularSubTotal() {
        double soma = 0.0;
        for (Item item : itens) {
            soma += item.getSubTotal();
        }
        return soma;
    }

    private double calcularDescontoProduto() {
        double soma = 0.0;
        double desconto;

        for (Item item : itens) {
            if (item.getProduto().getTipoProduto().equals(EnumTipoProduto.PRODUTO)
                    && situacao.equals(EnumSituacaoPedido.ABERTO)) {
                soma += item.getSubTotal();
            }
        }
        desconto = (this.desconto * soma) / 100;

        if (desconto > soma) {
            throw new RegraNegocioException("O valor do desconto não pode ser maior do o total de produtos.");
        }
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
        this.totalPedido = getSubTotalPedido() - this.desconto;
    }

    @PreUpdate
    private void update(){
        this.subTotalPedido = calcularSubTotal();
        this.desconto = calcularDescontoProduto();
        this.totalPedido = getSubTotalPedido() - this.desconto;
    }

}
