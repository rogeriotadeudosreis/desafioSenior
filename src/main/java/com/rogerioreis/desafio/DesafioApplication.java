package com.rogerioreis.desafio;

import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Cliente rogerio = new Cliente(null, "Rogerio", "rogerio@gmail.com", ZonedDateTime.now(), ZonedDateTime.now());
//        clienteRepository.save(rogerio);
//        System.out.println(rogerio.toString());


    }
}
