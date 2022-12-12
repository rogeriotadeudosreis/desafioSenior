package com.rogerioreis.desafio.service;

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

        ItemPedido itemPedidoSalvo = this.itemPedidoRepository.save(itemPedido);

        return itemPedidoSalvo;

    }

    public ItemPedido readById(Long id) {

        ItemPedido itemPedido = itemPedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("ItemPedido não encontrado."));

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
}
