package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.repositories.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

}
