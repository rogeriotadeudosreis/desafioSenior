package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.rogerioreis.desafio.enuns.EnumSituacaoPedido;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "PEDIDO")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATA_INICIO", nullable = false, updatable = false)
    private ZonedDateTime dataInicio;

    @Column(name = "DATA_FIM")
    private ZonedDateTime dataFim;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", nullable = false, foreignKey = @ForeignKey(name = "FK_CLIENTE"))
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(name = "TOTAL")
    private double total;

    @Column(name = "DESCONTO")
    @Digits(integer = 9, fraction = 2)
    private double desconto;

    @Column(name = "SITUACAO", length = 10)
    @Enumerated(EnumType.STRING)
    private EnumSituacaoPedido situacao = EnumSituacaoPedido.ABERTO;

    public Pedido(Long id, ZonedDateTime dataInicio, ZonedDateTime dataFim, Cliente cliente, List<ItemPedido> itens, double total, double desconto) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cliente = cliente;
        this.itens = itens;
        this.total = total;
        this.desconto = desconto;
    }

    public Pedido() {
    }

    public double getTotal() {
        double soma = 0.0;
        for (ItemPedido item : itens) {
            soma += item.getSubTotal();
        }
        return soma;
    }

    public void setTotal() {
        double soma = 0.0;
        for (ItemPedido item : itens) {
            soma += item.getSubTotal();
        }
        this.total = soma;
    }

    @JsonGetter
    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }

    @PrePersist
    private void init() {
        this.dataInicio = ZonedDateTime.now();
        this.situacao = EnumSituacaoPedido.FECHADO;
    }


}
