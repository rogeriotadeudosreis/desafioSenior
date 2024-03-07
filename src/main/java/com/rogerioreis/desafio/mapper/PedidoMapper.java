package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.PedidoRequest;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.model.Pedido;
import com.rogerioreis.desafio.model.Produto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PedidoMapper {

    public Pedido toEntity(PedidoRequest pedidoRequest) {
        if (pedidoRequest != null) {
            List<Item> itens = pedidoRequest.itens();
            if (!itens.isEmpty()) {
                for (Item item : itens) {
                    Produto produto = item.getProduto();
                    if (produto == null) {
                        throw new RegraNegocioException("A lista de itens deve conter um produto.");
                    }
                }
            } else {
                throw new RegraNegocioException("O pedido não contem a lista de itens para finalizar a compra.");
            }
        } else {
            throw new RegraNegocioException("É necessário um objeto do tipo PedidoRequest para salvar o pedido.");
        }
        return new Pedido(null, null, null, null,
                pedidoRequest.cliente(), pedidoRequest.itens(), BigDecimal.ZERO, BigDecimal.ZERO,
                pedidoRequest.desconto(), pedidoRequest.situacao());
    }
}
