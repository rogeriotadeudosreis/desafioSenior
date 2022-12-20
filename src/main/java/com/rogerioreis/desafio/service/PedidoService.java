package com.rogerioreis.desafio.service;

import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.exception.RequisicaoComErroException;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.model.Pedido;
import com.rogerioreis.desafio.repository.ClienteRepository;
import com.rogerioreis.desafio.repository.PedidoRepository;
import com.rogerioreis.desafio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public Pedido create(Pedido pedido) {

        validaPedido(pedido);

        Pedido pedidoSalvo = this.pedidoRepository.save(pedido);

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

        Pedido pedido = this.readById(id);

        pedidoForm.setId(pedido.getId());
        pedidoForm.setDataFim(pedido.getDataFim());
        pedidoForm.setDataInicio(pedido.getDataInicio());
        pedidoForm.setCliente(pedido.getCliente());

        return pedidoRepository.save(pedidoForm);

    }

    public void delete(Long id) {

        Pedido pedido = readById(id);

        pedido.setDataFim(ZonedDateTime.now());

        this.pedidoRepository.save(pedido);

    }

    public void validaPedido(Pedido pedido) {
        clienteRepository.findById(pedido.getCliente().getId())
                .ifPresent(cliente -> {
                    if (!cliente.isAtivo()) {
                        throw new RegraNegocioException("O cliente + [" + cliente.getId() + "] deste pedido está desativado.");
                    }
                });

        List<Item> itens = pedido.getItens();

        itens.stream().forEach(item -> {
            if (item.getProduto().getDataFim() != null)
                throw new RegraNegocioException("O produto deste item está DESATIVADO no cadastro de produtos.");
        });

    }
}









