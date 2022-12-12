package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

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
    private double price;

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

    public ItemPedido(Long id, Integer quantidade, double price, double desconto, Produto produto, Pedido pedido) {
        this.id = id;
        this.quantidade = quantidade;
        this.price = price;
        this.desconto = desconto;
        this.produto = produto;
        this.pedido = pedido;
    }

    public ItemPedido() {
    }
}
