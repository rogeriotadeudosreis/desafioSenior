package com.rogerioreis.desafio.dto;


import com.rogerioreis.desafio.enuns.EnumSituacaoPedido;
import com.rogerioreis.desafio.model.ItemPedido;
import com.rogerioreis.desafio.model.Pedido;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoFormDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private ZonedDateTime dataFim;

    private ClienteFormDto clienteFormDto;

    private double desconto;

    private EnumSituacaoPedido situacao;

    private List<ItemPedido> itens = new ArrayList<>();

    public PedidoFormDto(Pedido pedido){}


}
