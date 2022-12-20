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

    @Min(value = 1, message = "Informe pelo menos um item do pedido.")
    @NotNull(message = "informe a quantidade do item do pedido")
    private Integer Quantidade;

    @Digits(integer = 9, fraction = 2)
    @NotNull(message = "O preço do produto deve ser informado.")
    private double preco;

    @NotNull(message = "O id do produto deve ser informado.")
    private Produto produto;

    @NotNull(message = "O id do pedido deve ser informado.")
    private List<Pedido> pedidos = new ArrayList<>();

    public ItemFormDto(ItemFormDto itemPedido){

    }


}
