package com.rogerioreis.desafio.service;

import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.model.Produto;
import com.rogerioreis.desafio.repository.ItemPedidoRepository;
import com.rogerioreis.desafio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public Item create(Item item) {

        validaItemPedido(item);

        return this.itemPedidoRepository.save(item);

    }

    public Item readById(Long id) {

        Item item = itemPedidoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("ItemPedido não encontrado."));

        return item;

    }

    public Page<Item> page(Pageable pageable) {

        return itemPedidoRepository.findAll(pageable);
    }

    public Item update(Long id, Item itemForm) {

        Item item = this.readById(id);

        itemForm.setId(item.getId());

        return itemPedidoRepository.save(itemForm);

    }

    public void delete(Long id) {

        Item item = readById(id);

        this.itemPedidoRepository.delete(item);

    }

    public void validaItemPedido(Item item) {

        Produto produtoConsultado = this.produtoRepository
                .findById(item.getProduto().getId()).orElseThrow(() ->
                        new RecursoNaoEncontradoException("Produto de id [" + item.getProduto().getId() + "] não encontrado.")
                );

        boolean isProdAtivo = produtoConsultado.isAtivo();

        if (item.getQuantidade() <= 0) {
            throw new RegraNegocioException("A quantidade de itens deve ser informada");
        }

        if (item.getPreco() <= 0) {
            throw new RegraNegocioException("O preço do item não pode menor ou igual a zero.");
        }

        if (!isProdAtivo) {
            throw new RegraNegocioException("O produto deste item está DESATIVADO.");
        }

    }
}
