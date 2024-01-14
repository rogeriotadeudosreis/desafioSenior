package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.model.Order;
import com.rogerioreis.desafio.model.Product;
import lombok.*;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ItemRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Min(value = 1, message = "{quantity.min}")
    @NotNull(message = "{quantity.not.null}")
    private Integer quantidade;

    @Digits(integer = 9, fraction = 2, message = "{price.not.valid}")
    @NotNull(message = "{price.not.null}")
    private double preco;

    @NotNull(message = "{product.not.null}")
    private Product produto;

    @NotNull(message = "{order.not.null}")
    private List<Order> pedidos = new ArrayList<>();

    public ItemRequest(ItemRequest itemPedido){

    }


}
