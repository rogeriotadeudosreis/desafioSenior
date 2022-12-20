package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.model.Pedido;
import com.rogerioreis.desafio.model.Produto;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ItemFormDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Min(value = 1, message = "{quantity.min}")
    @NotNull(message = "{quantity.not.null}")
    private Integer quantidade;

    @Digits(integer = 9, fraction = 2, message = "{price.not.valid}")
    @NotNull(message = "{price.not.null}")
    private double preco;

    @NotNull(message = "{product.not.null}")
    private Produto produto;

    @NotNull(message = "{order.not.null}")
    private List<Pedido> pedidos = new ArrayList<>();

    public ItemFormDto(ItemFormDto itemPedido){

    }


}
