package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumTipoProduto;

import java.math.BigDecimal;

public record ItemResponse(Long id, int quantidade, BigDecimal preco, Long idProduto,
                           String nomeProduto, String codigoProduto, EnumTipoProduto tipoProduto, Boolean ativo,
                           BigDecimal subTotal) {
}
