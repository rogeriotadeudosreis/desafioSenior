package com.rogerioreis.desafio;

import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.ItemPedido;
import com.rogerioreis.desafio.model.Pedido;
import com.rogerioreis.desafio.model.Produto;
import com.rogerioreis.desafio.repository.ClienteRepository;
import com.rogerioreis.desafio.repository.ItemPedidoRepository;
import com.rogerioreis.desafio.repository.PedidoRepository;
import com.rogerioreis.desafio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.util.ArrayStack;

import java.time.ZonedDateTime;
import java.util.Arrays;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Cliente cliente = clienteRepository.findById(7L).get();
        System.out.println(cliente.getNome());
//        Pedido pedido3 = new Pedido(null, null, null, cliente );
//        pedidoRepository.save(pedido3);

//        Produto prod1 = produtoRepository.findById(1L).get();
//        Produto prod2 = produtoRepository.findById(3L).get();
//
//        Pedido pedido1 = pedidoRepository.findById(1L).get();
//        Pedido pedido2 = pedidoRepository.findById(2L).get();
//
//        ItemPedido item1 = new ItemPedido(null, 10, 100.00, 0.0, prod1, pedido1);
//        ItemPedido item2 = new ItemPedido(null, 10, 100.00, 0.0, prod2, pedido2);
//
//        itemPedidoRepository.saveAll(Arrays.asList(item1,item2));



    }
}
