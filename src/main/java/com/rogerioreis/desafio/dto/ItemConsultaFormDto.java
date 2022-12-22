package com.rogerioreis.desafio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ItemConsultaFormDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer quantidade;

    private ProdutoConsultaPedidoDto produto;

    private double preco;

    private double subTotal;

    @JsonIgnore
    private List<PedidoConsultaFormDto> pedidos = new ArrayList<>();

}
