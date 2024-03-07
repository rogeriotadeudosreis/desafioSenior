package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enun.EnumSituacaoPedido;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.Item;

import java.math.BigDecimal;
import java.util.List;

public record PedidoRequest(
        Long id,
        Cliente cliente,
        List<Item> itens,
        BigDecimal desconto,
        EnumSituacaoPedido situacao
) {
}
