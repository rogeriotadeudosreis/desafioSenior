package com.rogerioreis.desafio.service;

import com.rogerioreis.desafio.dto.PedidoRequest;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.mapper.PedidoMapper;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.model.Pedido;
import com.rogerioreis.desafio.repository.ClienteRepository;
import com.rogerioreis.desafio.repository.PedidoRepository;
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

    @Autowired
    private ItemService itemService;

    @Autowired
    private PedidoMapper pedidoMapper;

    public Pedido create(PedidoRequest pedidoRequest) {

        Pedido pedido = pedidoMapper.toEntity(pedidoRequest);

        validaPedido(pedido);

        Long lastId = pedidoRepository.findTopByOrderByIdDesc();
        Long nextNumeroPedido = lastId + 1;

        pedido.setNumero("PED Nº: " + nextNumeroPedido);

        Pedido pedidoSalvo = this.pedidoRepository.save(pedido);

        pedidoSalvo.getItens().forEach(item -> {
            item.setPedido(pedidoSalvo);
            itemService.create(item);
        });

        return pedidoSalvo;

    }

    public Pedido readById(Long id) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado."));

        return pedido;

    }

    public Page<Pedido> page(String descricao, Pageable pageable) {

        String desc = descricao != null ? descricao : "";

        return pedidoRepository.findAllByClienteLikeIgnoreCase(desc, pageable);
    }

    public Pedido update(Long id, Pedido pedidoForm) {

        pedidoForm.setId(id);
        validaPedido(pedidoForm);

        Pedido pedidoUpdate = pedidoRepository.save(pedidoForm);

        return pedidoUpdate;

    }

    public void delete(Long id) {

        Pedido pedido = readById(id);

        this.pedidoRepository.delete(pedido);

    }

    public void validaPedido(Pedido pedido) {

        Long clienteId = pedido.getCliente().getId();

        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() ->
                new RecursoNaoEncontradoException("O cliente com ID [" + clienteId + "] não consta no sistema.")
        );

//        if (!cliente.isAtivo()) {
//            throw new RegraNegocioException("O cliente [" + cliente.getId() + "] deste pedido está desativado.");
//        }

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









