package com.rogerioreis.desafio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ItemResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer quantidade;

    private ProductOrderResponse produto;

    private double preco;

    private double subTotal;

    @JsonIgnore
    private List<OrderResponse> pedidos = new ArrayList<>();

}
