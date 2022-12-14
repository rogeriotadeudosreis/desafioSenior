package com.rogerioreis.desafio.service;

import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.model.Pedido;
import com.rogerioreis.desafio.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;


    public Pedido create(Pedido pedido) {

        Pedido pedidoSalvo = this.pedidoRepository.save(pedido);

        return pedidoSalvo;

    }

    public Pedido readById(Long id) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado."));

        return pedido;

    }

    public Page<Pedido> page(String descricao, Pageable pageable) {

        String desc = descricao != null ? descricao : "";

        return pedidoRepository.findAllByClienteLikeIgnoreCase(desc,  pageable);
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
}
