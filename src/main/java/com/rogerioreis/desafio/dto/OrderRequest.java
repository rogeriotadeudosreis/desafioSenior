package com.rogerioreis.desafio.dto;


import com.rogerioreis.desafio.enuns.EnumSituacaoPedido;
import com.rogerioreis.desafio.model.Client;
import com.rogerioreis.desafio.model.Item;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private double desconto;

    @NotNull(message = "{client.not.null}")
    private Client cliente;

    private EnumSituacaoPedido situacao;

    @NotNull(message = "{item.not.null}")
    private List<Item> itens = new ArrayList<>();

}
