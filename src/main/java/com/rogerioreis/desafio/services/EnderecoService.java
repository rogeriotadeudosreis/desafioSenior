package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.model.Contato;
import com.rogerioreis.desafio.model.Endereco;
import com.rogerioreis.desafio.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco create(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    protected List<Endereco> createEnderecoByContato(Contato contato, List<Endereco> enderecos) {
        if (contato != null && enderecos != null && !enderecos.isEmpty()) {
            List<Endereco> listEnderecos = new ArrayList<>();
            for (Endereco endereco : enderecos) {
                if (!enderecos.isEmpty()) {
                    Endereco enderecoSalvar = new Endereco(endereco.getId(), endereco.getEstado(), endereco.getLocalidade(),
                            endereco.getCep(), endereco.getBairro(), endereco.getLogradouro(), endereco.getNumero(),
                            endereco.getComplemento(), endereco.getTipoEndereco(), endereco.getDataInicio(),
                            endereco.getDataFim(), endereco.getDataAtualizacao(), contato);
                    listEnderecos.add(enderecoRepository.save(enderecoSalvar));
                } else {
                    throw new RegraNegocioException("É necessário informar pelo menos 01(um) endereco válido.");
                }
            }
            return listEnderecos;
        } else {
            throw new RegraNegocioException("A lista de enderecos está nula ou vazia.");
        }
    }

    public List<Endereco> findAllByContatoId(Long idCliente) {
        List<Endereco> enderecoList = enderecoRepository.findAllByContatoId(idCliente);
        return enderecoList;
    }


}
