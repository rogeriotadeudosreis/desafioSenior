package com.rogerioreis.desafio;

import com.rogerioreis.desafio.enuns.EnumSituacaoPedido;
import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.Item;
import com.rogerioreis.desafio.model.Pedido;
import com.rogerioreis.desafio.model.Produto;
import com.rogerioreis.desafio.repository.ClienteRepository;
import com.rogerioreis.desafio.repository.ItemRepository;
import com.rogerioreis.desafio.repository.PedidoRepository;
import com.rogerioreis.desafio.repository.ProdutoRepository;
import com.rogerioreis.desafio.service.PedidoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class PedidoRepositoryImplTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void inserirNovoPedidoTest() {

        Cliente c4 = new Cliente(null, "CLIENTE 04", "cliente04@gmail.com");
        clienteRepository.save(c4);
        Produto prod7 = new Produto(null, "AR CONDICIONADO", "COD07", 1500.0, null, null, null, EnumTipoProduto.PRODUTO);
        produtoRepository.save(prod7);
        Item item04 = new Item(null, 1, 1500.0, prod7, 1500.0);
        itemRepository.save(item04);
        List<Item> lista01 = new ArrayList<>();
        lista01.add(item04);

        Pedido ped01 = new Pedido(null, null, c4, lista01, 0.10, EnumSituacaoPedido.ABERTO);
        ped01.setTotalPedido(1.0);
        Pedido ped01Savo = pedidoService.create(ped01);

        assertEquals("PED Nº: 4", String.valueOf(ped01Savo.getNumeroPedido()));
        assertNotNull(ped01Savo.getCliente());
        assertNotNull(ped01Savo.getItens());
        assertEquals("FECHADO", String.valueOf(ped01Savo.getSituacao()));
        assertTrue(ped01Savo.isAtivo());
        assertEquals(1350.0, ped01Savo.getTotalPedido(), 0.0);


    }
}












