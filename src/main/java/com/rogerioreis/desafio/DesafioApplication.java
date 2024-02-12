package com.rogerioreis.desafio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DesafioApplication {

    public static void main(String[] args) {

        SpringApplication.run(DesafioApplication.class, args);
        log.info("mensagem info");
        log.error("mensagem error");
        log.warn("mensagem warn");
        log.debug("mensagem debug");
        log.trace("mensagem trace");

    }

}