package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.model.PessoaJuridica;
import com.rogerioreis.desafio.model.Telefone;
import com.rogerioreis.desafio.repositories.EmailRepository;
import com.rogerioreis.desafio.repositories.PessoaJuridicaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TelefoneService telefoneService;

    public PessoaJuridica create(PessoaJuridica pessoaJuridica) {
        PessoaJuridica pessoaJuridicaSalvar = new PessoaJuridica(pessoaJuridica);
        pessoaJuridicaSalvar.setId(null);

        Set<Email> emails = pessoaJuridica.getContato().getEmails();
        Set<Telefone> telefones = pessoaJuridica.getContato().getTelefones();

        pessoaJuridicaSalvar = this.pessoaRepository.save(pessoaJuridicaSalvar);

        createEmails(pessoaJuridicaSalvar, emails);
        createTelefones(pessoaJuridicaSalvar, telefones);

        return pessoaJuridicaSalvar;
    }

    private void createEmails(PessoaJuridica pessoaJuridicaSalvar, Set<Email> emails) {
        if (emails != null && !emails.isEmpty()) {
            for (Email email : emails) {
                if (StringUtils.isNotBlank(email.getEmail())) {
                    Email emailSalvar = new Email(null, email.getEmail(), email.getTipo(), null,
                            null, null, pessoaJuridicaSalvar.getPessoa().getContato());
                    emailService.create(emailSalvar);
                } else {
                    throw new RegraNegocioException("É necessário informar pelo menos 01(um) email válido.");
                }
            }
        } else {
            throw new RegraNegocioException("A lista de emails está nula ou vazia.");
        }
    }

    private void createTelefones(PessoaJuridica pessoaJuridicaSalvar, Set<Telefone> telefones) {
        if (telefones != null && !telefones.isEmpty()) {
            for (Telefone telefone : telefones) {
                if (StringUtils.isNotBlank(telefone.getTelefone())) {
                    Telefone telefonesalvar = new Telefone(null, telefone.getTelefone(), telefone.getDdd(),
                            telefone.getDdi(), telefone.getTipoTelefone(), pessoaJuridicaSalvar.getPessoa().getContato(),
                            null, null, null);
                    telefoneService.create(telefonesalvar);
                } else {
                    throw new RegraNegocioException("É necessário informar pelo menos 01(um) telefone válido.");
                }

            }
        } else {
            throw new RegraNegocioException("A lista de telefones está nula ou vazia.");
        }
    }
}
