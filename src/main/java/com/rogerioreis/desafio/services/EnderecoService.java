package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.enuns.EnumTipoEndereco;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.Endereco;
import com.rogerioreis.desafio.repositories.EnderecoRepository;
import org.apache.commons.lang3.StringUtils;
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

    public List<Endereco> listByCliente(Long clienteId) {
        return null;
    }

    protected List<Endereco> createEnderecoByCliente(Cliente cliente, List<Endereco> enderecos) {
        if (cliente != null && enderecos != null && !enderecos.isEmpty()) {
            List<Endereco> listEnderecos = new ArrayList<>();
            for (Endereco endereco : enderecos) {
                if (!enderecos.isEmpty()) {
                    Endereco enderecoSalvar = new Endereco(endereco.getId(), endereco.getEstado(), endereco.getCidade(),
                            endereco.getCep(), endereco.getBairro(), endereco.getLogradouro(), endereco.getNumero(),
                            endereco.getComplemento(), endereco.getTipoEndereco(), cliente);
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

    public List<Endereco> findAllByClienteId(Long idCliente) {
        List<Endereco> enderecoList = enderecoRepository.findAllByClienteId(idCliente);
        return enderecoList;
    }

    ;
}
