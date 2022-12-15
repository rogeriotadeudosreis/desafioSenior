package com.rogerioreis.desafio;

import com.rogerioreis.desafio.repository.ClienteRepository;
import com.rogerioreis.desafio.repository.ItemPedidoRepository;
import com.rogerioreis.desafio.repository.PedidoRepository;
import com.rogerioreis.desafio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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





    }
}
