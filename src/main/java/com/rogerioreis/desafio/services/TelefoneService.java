package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.model.Telefone;
import com.rogerioreis.desafio.repositories.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    public Telefone create(Telefone telefone) {
        return telefoneRepository.save(telefone);
    }
}
