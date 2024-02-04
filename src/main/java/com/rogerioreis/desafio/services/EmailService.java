package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public Email create(Email email) {
        return emailRepository.save(email);
    }

    public List<Email> listByContato(Long contatoId) {
        return null;
    }
}
