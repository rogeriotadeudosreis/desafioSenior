package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.model.Client;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.model.Order;
import com.rogerioreis.desafio.repositories.ClienteRepository;
import com.rogerioreis.desafio.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Order create(Order pedido) {

        validaPedido(pedido);

        Order pedidoSalvo = this.pedidoRepository.save(pedido);

        return pedidoSalvo;

    }

    public Order readById(Long id) {

        Order pedido = pedidoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado."));

        return pedido;

    }

    public Page<Order> page(String descricao, Pageable pageable) {

        String desc = descricao != null ? descricao : "";

        return pedidoRepository.findAllByClienteLikeIgnoreCase(desc, pageable);
    }

    public Order update(Long id, Order pedidoForm) {

        pedidoForm.setId(id);
        validaPedido(pedidoForm);

        Order pedidoUpdate = pedidoRepository.save(pedidoForm);

        return pedidoUpdate;

    }

    public void delete(Long id) {

        Order pedido = readById(id);

        this.pedidoRepository.delete(pedido);

    }

    public void validaPedido(Order pedido) {

        Long clienteId = pedido.getCliente().getId();

        Client cliente = clienteRepository.findById(clienteId).orElseThrow(() ->
                new RecursoNaoEncontradoException("O cliente com ID [" + clienteId + "] não consta no sistema.")
        );

        if (!cliente.isAtivo()) {
            throw new RegraNegocioException("O cliente [" + cliente.getId() + "] deste pedido está desativado.");
        }

        List<Item> itens = pedido.getItens();



        if (itens.size() == 0) {
            throw new RecursoNaoEncontradoException("A lista de itens está vazia.");
        } else {
            itens.stream().forEach(item -> {
                if (item.getProduto().getDataFim() != null)
                    throw new RegraNegocioException("O produto deste item está DESATIVADO no cadastro de produtos.");

                if (item.getPreco().compareTo(BigDecimal.ZERO) == 0 ||
                        item.getPreco().compareTo(BigDecimal.ZERO) < 0)
                    throw new RegraNegocioException("O PREÇO deste item não pode ser zero(0).");
            });
        }

    }
}









