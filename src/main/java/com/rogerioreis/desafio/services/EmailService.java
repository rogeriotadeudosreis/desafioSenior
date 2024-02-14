package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.model.Contato;
import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.repositories.EmailRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    protected Set<Email> createEmailByContato(Contato contato, Set<Email> emails) {
        if (contato != null && emails != null && !emails.isEmpty()) {
            Set<Email> listEmails = new HashSet<>();
            for (Email email : emails) {
                if (StringUtils.isNotBlank(email.getEmail())) {
                    Email emailSalvar = new Email(email.getId(), email.getEmail(), email.getTipo(), null,
                            null, null, contato);
                    listEmails.add(emailRepository.save(emailSalvar));
                } else {
                    throw new RegraNegocioException("É necessário informar pelo menos 01(um) email válido.");
                }
            }
            return listEmails;
        } else {
            throw new RegraNegocioException("A lista de emails está nula ou vazia.");
        }
    }

    public Set<Email> findEmailsByContato(Long idcontato){
        Set<Email> emails = emailRepository.findAllByContatoId(idcontato);
        return emails;
    }
}
