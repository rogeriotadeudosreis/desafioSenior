package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
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

    @Column(name = "PRECO", nullable = false, scale = 2)
    private BigDecimal preco;

    @JsonIgnore
    @ManyToMany(mappedBy = "itens", fetch = FetchType.LAZY)
    private List<Order> pedidos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(name = "PRODUTO_FK"))
    private Produto produto;

    @Column(name = "SUBTOTAL", scale = 2)
    private BigDecimal subTotal;

    public Item(Long id, Integer quantidade, BigDecimal preco, Produto produto, BigDecimal subTotal) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
        this.produto = produto;
        this.subTotal = subTotal;
    }

    public BigDecimal getSubTotal() {
        return preco.multiply(new BigDecimal(quantidade));
    }

    @PrePersist
    private void init() {
        this.subTotal = getSubTotal();
    }
}
