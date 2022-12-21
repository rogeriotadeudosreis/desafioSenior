package com.rogerioreis.desafio;

import static org.junit.Assert.*;

import com.rogerioreis.desafio.model.Pedido;
import com.rogerioreis.desafio.service.PedidoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class PedidoServiceImplTest {

    @Autowired
    private PedidoService pedidoService;

    @Test
    public void readByIdTest() {
        Pedido pedido = pedidoService.readById(1L);
        ass
    }
}












