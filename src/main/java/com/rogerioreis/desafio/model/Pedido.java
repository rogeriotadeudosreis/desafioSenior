package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.rogerioreis.desafio.enuns.EnumSituacaoPedido;
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

    @Column(name = "NUM_PEDIDO", nullable = false, updatable = false)
    private String numeroPedido;

    @Column(name = "DATA_INICIO", nullable = false, updatable = false)
    private ZonedDateTime dataInicio;

    @Column(name = "DATA_FIM")
    private ZonedDateTime dataFim;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "CLIENTE_FK"))
    private Cliente cliente;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "item_pedido",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> itens = new ArrayList<>();

    @Column(name = "TOTAL")
    private double total;

    @Column(name = "DESCONTO")
    @Digits(integer = 9, fraction = 2)
    private double desconto;

    @Column(name = "SITUACAO", length = 10)
    @Enumerated(EnumType.STRING)
    private EnumSituacaoPedido situacao = EnumSituacaoPedido.ABERTO;

    public double getTotal() {
        double soma = 0.0;
        for (Item item : itens) {
            soma += item.getSubTotal();
        }
        return soma;
    }

    public void setTotal() {
        double soma = 0.0;
        for (Item item : itens) {
            soma += item.getSubTotal();
        }
        this.total = soma;
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido(Long id, String numeroPedido, Cliente cliente, double total, double desconto, EnumSituacaoPedido situacao) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.total = total;
        this.desconto = desconto;
        this.situacao = situacao;
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
