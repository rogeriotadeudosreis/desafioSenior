package com.rogerioreis.desafio.service;

import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.exception.RequisicaoComErroException;
import com.rogerioreis.desafio.model.ItemPedido;
import com.rogerioreis.desafio.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


    public ItemPedido create(ItemPedido itemPedido) {

        validaItemPedido(itemPedido);

        ItemPedido itemPedidoSalvo = this.itemPedidoRepository.save(itemPedido);

        return itemPedidoSalvo;

    }

    public ItemPedido readById(Long id) {

        ItemPedido itemPedido = itemPedidoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("ItemPedido não encontrado."));

        return itemPedido;

    }

    public Page<ItemPedido> page(Pageable pageable) {

        return itemPedidoRepository.findAll(pageable);
    }

    public ItemPedido update(Long id, ItemPedido itemPedidoForm) {

        ItemPedido itemPedido = this.readById(id);

        itemPedidoForm.setId(itemPedido.getId());

        return itemPedidoRepository.save(itemPedidoForm);

    }

    public void delete(Long id) {

        ItemPedido itemPedido = readById(id);

        this.itemPedidoRepository.delete(itemPedido);

    }

    public void validaItemPedido(ItemPedido item) {

        if (item.getQuantidade() <= 0) {
            throw new RegraNegocioException("A quantidade de itens deve ser informada");
        }

        if (item.getPreco() <= 0) {
            throw new RegraNegocioException("O preço do item não pode menor ou igual a zero.");
        }

        if (item.getProduto().getTipoProduto().equals(EnumTipoProduto.SERVICO)) {
            item.setDesconto(0.0);
        }

        if (item.getDesconto() > item.getPreco()) {
            throw new RequisicaoComErroException("O valor do desconto não pode ser maior do que o preço do produto.");
        }

    }
}
