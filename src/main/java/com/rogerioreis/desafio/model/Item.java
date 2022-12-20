package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "PRECO", nullable = false)
    private double preco;

    @JsonIgnore
    @ManyToMany(mappedBy = "itens", fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(name = "PRODUTO_FK"))
    private Produto produto;

    @Column(name = "SUBTOTAL")
    private double subTotal;

    public Item(Long id, Integer quantidade, double preco, Produto produto, double subTotal) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
        this.produto = produto;
        this.subTotal = subTotal;
    }

    public double getSubTotal() {
        return quantidade * preco;
    }

    @PrePersist
    private void init() {
        this.subTotal = this.quantidade * this.preco;
    }
}
