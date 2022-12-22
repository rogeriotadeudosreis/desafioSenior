package com.rogerioreis.desafio.dto;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rogerioreis.desafio.enuns.EnumSituacaoPedido;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.model.Pedido;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PedidoFormDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private double desconto;

    @NotNull(message = "{client.not.null}")
    private Cliente cliente;

    private EnumSituacaoPedido situacao;

    @NotNull(message = "{item.not.null}")
    private List<Item> itens = new ArrayList<>();

}
