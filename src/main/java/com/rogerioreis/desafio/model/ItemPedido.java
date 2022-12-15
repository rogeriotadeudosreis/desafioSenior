package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "ITEM_PEDIDO")
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUANTIDADE")
    @Min(value = 1, message = "Informe pelo menos um item do pedido.")
    @NotNull(message = "informe a quantidade do item do pedido")
    private Integer quantidade;

    @Column(name = "PRECO", nullable = false)
    @Digits(integer = 9, fraction = 2)
    @NotNull(message = "O preço do produto deve ser informado.")
    private double preco;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO", nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUTO"))
    private Produto produto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO", nullable = false, foreignKey = @ForeignKey(name = "FK_PEDIDO"))
    private Pedido pedido;

    @Column(name = "SUBTOTAL")
    private double subTotal;

    public ItemPedido(Long id, Integer quantidade, double preco, Produto produto, Pedido pedido, double subTotal) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
        this.produto = produto;
        this.pedido = pedido;
        this.subTotal = subTotal;
    }

    public ItemPedido() {
    }

    public double getSubTotal(){
        return quantidade * preco;
    }

    @PrePersist
    private void init(){
        this.subTotal = this.quantidade * this.preco;
    }
}
