package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "ITEM_PEDIDO")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUANTIDADE")
    @Min(value = 1, message = "Informe pelo menos um item ao pedido.")
    private Integer quantidade;

    @Column(name = "PRECO", nullable = false)
    @Digits(integer = 9, fraction = 2)
    private double preco;

    @Column(name = "DESCONTO")
    @Digits(integer = 9, fraction = 2)
    private double desconto;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO", nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUTO"))
    private Produto produto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO", nullable = false, foreignKey = @ForeignKey(name = "FK_PEDIDO"))
    private Pedido pedido;

    @Column(name = "SUBTOTAL")
    private double subTotal;

    public ItemPedido(Long id, Integer quantidade, double preco, double desconto, Produto produto, Pedido pedido, double subTotal) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
        this.desconto = desconto;
        this.produto = produto;
        this.pedido = pedido;
        this.subTotal = subTotal;
    }

    public ItemPedido() {
    }

    public double getSubTotal(){
        return quantidade * preco;
    }
}
