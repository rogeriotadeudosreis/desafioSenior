package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.model.Product;
import com.rogerioreis.desafio.repositories.ItemRepository;
import com.rogerioreis.desafio.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public Item create(Item item) {

        validaItemPedido(item);

        return this.itemRepository.save(item);

    }

    public Item readById(Long id) {

        Item item = itemRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Item não encontrado."));

        return item;

    }

    public Page<Item> page(String descricao, Pageable pageable) {

        String desc = descricao != null ? descricao : "";

        return itemRepository.findAllByProdutoLikeIgnoreCase(desc, pageable);
    }

    public Item update(Long id, Item itemForm) {

        Item item = this.readById(id);

        itemForm.setId(item.getId());

        return itemRepository.save(itemForm);

    }

    public void delete(Long id) {

        Item item = readById(id);

        this.itemRepository.delete(item);

    }

    public void validaItemPedido(Item item) {

        Product produtoConsultado = this.produtoRepository
                .findById(item.getProduto().getId()).orElseThrow(() ->
                        new RecursoNaoEncontradoException("Produto de id [" + item.getProduto().getId() + "] não encontrado.")
                );

        boolean isProdAtivo = produtoConsultado.isAtivo();

        if (item.getQuantidade() <= 0) {
            throw new RegraNegocioException("A QUANTIDADE de itens deve ser informada");
        }

        if (item.getPreco().compareTo(BigDecimal.ZERO) == 0 ||
                item.getPreco().compareTo(BigDecimal.ZERO) < 0) {
            throw new RegraNegocioException("O PREÇO do item não pode menor ou igual a zero.");
        }

        if (!isProdAtivo) {
            throw new RegraNegocioException("O produto deste item está DESATIVADO.");
        }

    }
}
