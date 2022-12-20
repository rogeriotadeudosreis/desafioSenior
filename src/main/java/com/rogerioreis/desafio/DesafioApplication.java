package com.rogerioreis.desafio;

import com.rogerioreis.desafio.enuns.EnumSituacaoPedido;
import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.model.Pedido;
import com.rogerioreis.desafio.model.Produto;
import com.rogerioreis.desafio.repository.ClienteRepository;
import com.rogerioreis.desafio.repository.ItemRepository;
import com.rogerioreis.desafio.repository.ProdutoRepository;
import com.rogerioreis.desafio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PedidoService pedidoService;

    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Produto prod1 = new Produto(null, "AR CONDICIONADO", "COD01", 1500.0, null, null, null, EnumTipoProduto.PRODUTO);
        Produto prod2 = new Produto(null, "INSTALAÇÃO AR CONDICIONADO", "COD02", 150.0, null, null, null, EnumTipoProduto.SERVICO);
        Produto prod3 = new Produto(null, "TV SONY BRAVIA", "COD03", 3000.0, null, null, null, EnumTipoProduto.PRODUTO);
        Produto prod4 = new Produto(null, "INSTALAÇÃO DE TV", "COD04", 150.0, null, null, null, EnumTipoProduto.SERVICO);
        Produto prod5 = new Produto(null, "CORTINA BLACKOUT", "COD05", 1000.0, null, null, null, EnumTipoProduto.PRODUTO);
        Produto prod6 = new Produto(null, "INSTALAÇÃO DE CORTINAS", "COD06", 150.0, null, null, null, EnumTipoProduto.SERVICO);
        produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6));

        Cliente c1 = new Cliente(null, "CLIENTE 01", "cliente01@gmail.com");
        Cliente c2 = new Cliente(null, "CLIENTE 02", "cliente02@gmail.com");
        Cliente c3 = new Cliente(null, "CLIENTE 03", "cliente03@gmail.com");
        clienteRepository.saveAll(Arrays.asList(c1, c2, c3));

        Item item01 = new Item(null, 1, 1500.0, prod1, 0.0);
        Item item02 = new Item(null, 1, 150.0, prod2, 0.0);
        List<Item> lista01 = new ArrayList<>();
        lista01.add(item01);
        lista01.add(item02);

        Item item03 = new Item(null, 1, 3000.0, prod3, 0.0);
        Item item04 = new Item(null, 1, 150.0, prod4, 0.0);
        List<Item> lista02 = new ArrayList<>();
        lista02.add(item03);
        lista02.add(item04);

        Item item05 = new Item(null, 1, 1000.0, prod5, 0.0);
        Item item06 = new Item(null, 1, 150.0, prod6, 0.0);
        List<Item> lista03 = new ArrayList<>();
        lista03.add(item05);
        lista03.add(item06);

        itemRepository.saveAll(Arrays.asList(item01, item02, item03, item04, item05, item06));


        Pedido ped1 = new Pedido(null, null, c1, lista01, 0.10, EnumSituacaoPedido.ABERTO);
        Pedido ped2 = new Pedido(null, null, c2, lista02, 0.10, EnumSituacaoPedido.ABERTO);
        Pedido ped3 = new Pedido(null, null, c3, lista03, 0.10, EnumSituacaoPedido.ABERTO);
        pedidoService.create(ped1);
        pedidoService.create(ped2);
        pedidoService.create(ped3);


    }
}
