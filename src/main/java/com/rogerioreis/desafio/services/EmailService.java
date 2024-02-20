package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.EmailRequest;
import com.rogerioreis.desafio.dto.EmailResponse;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.mapper.EmailMapper;
import com.rogerioreis.desafio.model.Contato;
import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.repositories.EmailRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmailMapper emailMapper;

    public EmailResponse create(EmailRequest emailRequest) {
        Email email = emailMapper.toEntity(emailRequest);
        email.setId(null);
        emailRepository.save(email);
        EmailResponse emailResponse = emailMapper.toDTO(email);
        return emailResponse;
    }

    public List<EmailResponse> findAllEmailsResponsesByContatoId(Long contatoId) {
        if (contatoId == null) {
            throw new RegraNegocioException("É necessário informar o id do contato para esta consulta.'");
        }
        List<Email> emails = emailRepository.findAllByContatoId(contatoId);
        if (!emails.isEmpty()) {
            return emailMapper.toListDTO(emails);
        } else {
            throw new RecursoNaoEncontradoException("não foi possível encontrar emails para este id de contato");
        }
    }

    public List<Email> findAllEmailsByContatoId(Long contatoId) {
        if (contatoId == null) {
            throw new RegraNegocioException("É necessário informar o id do contato para esta consulta.'");
        }
        List<Email> emailList = emailRepository.findAllByContatoId(contatoId);
        if (!emailList.isEmpty()) {
            return emailList;
        } else {
            throw new RecursoNaoEncontradoException("não foi possível encontrar emails para este id de contato");
        }

    }

    public List<EmailResponse> createEmailByContato(Contato contato, List<Email> emails) {
        if (contato != null && emails != null && !emails.isEmpty()) {
            List<EmailResponse> emailResponseList = new ArrayList<>();
            for (Email email : emails) {
                if (StringUtils.isNotBlank(email.getEmail())) {
                    Email emailSalvar = new Email(email.getId(), email.getEmail(), email.getTipo(), null,
                            null, null, contato);
                    emailResponseList.add(emailMapper.toDTO(emailRepository.save(emailSalvar)));
                } else {
                    throw new RegraNegocioException("É necessário informar pelo menos 01(um) email válido.");
                }
            }
            return emailResponseList;
        } else {
            throw new RegraNegocioException("A lista de emails está nula ou vazia.");
        }
    }

    public Email findEmailById(Long id) {
        if (id == null) {
            throw new RegraNegocioException("É necessário informar o id do email para esta consulta.'");
        }
        Email email = emailRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de email com o id [" + id + "] não encontrado"));
        return email;
    }

    public EmailResponse findEmailResponseById(Long id) {
        if (id == null) {
            throw new RegraNegocioException("É necessário informar o id do contato para esta consulta.'");
        }
        Email email = emailRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de email com o id [" + id + "] não encontrado"));
        return emailMapper.toDTO(email);
    }

    public void deleteById(EmailRequest emailRequest) {
        if (emailRequest == null) {
            throw new RegraNegocioException("É necessário informar um email para prosseguir com o delete.'");
        }
        Long id = emailRequest.id();
        this.findEmailById(id);
        emailRepository.deleteById(id);
    }


    public List<EmailResponse> createEmails(List<EmailRequest> emails) {
        if (emails != null && !emails.isEmpty()) {
            List<EmailResponse> emailResponseList = new ArrayList<>();
            for (EmailRequest emailRequest : emails) {
                Contato contato = emailRequest.contato();
                if (StringUtils.isNotBlank(emailRequest.email())) {
                    Email emailSalvar = new Email(emailRequest.id(), emailRequest.email(), emailRequest.tipo(), null,
                            null, null, contato);
                    emailResponseList.add(emailMapper.toDTO(emailRepository.save(emailSalvar)));
                } else {
                    throw new RegraNegocioException("É necessário informar pelo menos 01(um) email válido.");
                }
            }
            return emailResponseList;
        } else {
            throw new RegraNegocioException("A lista de emails está nula ou vazia.");
        }
    }
}
