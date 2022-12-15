package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.model.ItemPedido;

import java.io.Serializable;

public class ItemPedidoFormDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer Quantidade;

    private double preco;

    private ProdutoFormDto produtoDto;

    private PedidoFormDto pedidoDto;

    public ItemPedidoFormDto(ItemPedido itemPedido){

    }


}
