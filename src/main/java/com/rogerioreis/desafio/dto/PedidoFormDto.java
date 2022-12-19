package com.rogerioreis.desafio.dto;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.rogerioreis.desafio.enuns.EnumSituacaoPedido;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.model.Pedido;
import lombok.*;

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

    private Long id;

    private ZonedDateTime dataFim;

    private ClienteFormDto clienteFormDto;

    private double desconto;

    private EnumSituacaoPedido situacao;

    private List<Item> itens = new ArrayList<>();

    public PedidoFormDto(Pedido pedido){}

    @JsonGetter
    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }


}
