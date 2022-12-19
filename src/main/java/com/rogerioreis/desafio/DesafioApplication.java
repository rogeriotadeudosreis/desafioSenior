package com.rogerioreis.desafio;

import com.rogerioreis.desafio.enuns.EnumSituacaoPedido;
import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.model.Pedido;
import com.rogerioreis.desafio.model.Produto;
import com.rogerioreis.desafio.repository.ClienteRepository;
import com.rogerioreis.desafio.repository.ItemPedidoRepository;
import com.rogerioreis.desafio.repository.PedidoRepository;
import com.rogerioreis.desafio.repository.ProdutoRepository;
import com.rogerioreis.desafio.service.ClienteService;
import com.rogerioreis.desafio.service.ItemPedidoService;
import com.rogerioreis.desafio.service.PedidoService;
import com.rogerioreis.desafio.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private PedidoService pedidoService;

    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Produto prod1 = new Produto(null, "PRODUTO 01", "COD01", 15.0, null, null, null, EnumTipoProduto.PRODUTO);
        Produto prod2 = new Produto(null, "PRODUTO 02", "COD02", 15.0, null, null, null, EnumTipoProduto.SERVICO);
        Produto prod3 = new Produto(null, "PRODUTO 03", "COD03", 15.0, null, null, null, EnumTipoProduto.PRODUTO);
        produtoService.create(prod1);
        produtoService.create(prod2);
        produtoService.create(prod3);


        Cliente c1 = new Cliente(null, "CLIENTE 01", "cliente01@gmail.com");
        Cliente c2 = new Cliente(null, "CLIENTE 02", "cliente02@gmail.com");
        Cliente c3 = new Cliente(null, "CLIENTE 03", "cliente03@gmail.com");
        clienteService.create(c1);
        clienteService.create(c2);
        clienteService.create(c3);

        Item item01 = new Item(null, 2, 25.0, prod1, 50.0 );
        Item item02 = new Item(null, 2, 25.0, prod2, 50.0 );
        Item item03 = new Item(null, 2, 25.0, prod3, 50.0 );
        itemPedidoService.create(item01);
        itemPedidoService.create(item02);
        itemPedidoService.create(item03);

        Pedido ped1 = new Pedido(null, "ped-01",c1, 0.0, 0.0, EnumSituacaoPedido.FECHADO);
        Pedido ped2 = new Pedido(null, "ped-02",c2, 0.0, 0.0, EnumSituacaoPedido.FECHADO);
        Pedido ped3 = new Pedido(null, "ped-03",c3, 0.0, 0.0, EnumSituacaoPedido.FECHADO);
        pedidoService.create(ped1);
        pedidoService.create(ped2);
        pedidoService.create(ped3);






    }
}
