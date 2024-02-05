package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.model.Contato;
import com.rogerioreis.desafio.model.Telefone;
import com.rogerioreis.desafio.repositories.TelefoneRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    public Telefone create(Telefone telefone) {
        return telefoneRepository.save(telefone);
    }

    protected void createTelefoneByContato(Contato contato, Set<Telefone> telefones) {
        if (contato != null && telefones != null && !telefones.isEmpty()) {
            for (Telefone telefone : telefones) {
                if (StringUtils.isNotBlank(telefone.getTelefone())) {
                    Telefone telefoneSalvar = new Telefone(null, telefone.getTelefone(), telefone.getDdd(),
                            telefone.getDdi(), telefone.getTipoTelefone(), contato,
                            null, null, null);
                    telefoneRepository.save(telefoneSalvar);
                } else {
                    throw new RegraNegocioException("É necessário informar pelo menos 01(um) telefone válido.");
                }
            }
        } else {
            throw new RegraNegocioException("A lista de telefones está nula ou vazia.");
        }
    }
}
