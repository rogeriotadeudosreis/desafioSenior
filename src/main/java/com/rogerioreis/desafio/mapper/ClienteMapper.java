package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.ClienteResponse;
import com.rogerioreis.desafio.dto.EnderecoResponse;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    @Autowired
    private PessoaFisicaMapper pessoaFisicaMapper;

    @Autowired
    private PessoaJuridicaMapper pessoaJuridicaMapper;

    @Autowired
    private ContatoMapper contatoMapper;

    @Autowired
    private EnderecoMapper enderecoMapper;

    public ClienteResponse toDTO(Cliente cliente) {

        return new ClienteResponse(
                cliente.getId(),
                cliente.getSituacao(),
                contatoMapper.toDTO(cliente.getContato()),
                pessoaFisicaMapper.toDTO(cliente.getPessoaFisica()),
                pessoaJuridicaMapper.toDTO(cliente.getPessoaJuridica())
        );
    }

    public List<ClienteResponse> toListDTO(List<Cliente> clienteList) {

        if (clienteList == null || clienteList.isEmpty()) {
            throw new RecursoNaoEncontradoException("A lista de clientes está nula ou vazia.");
        }

        List<ClienteResponse> clienteResponseList = clienteList.stream()
                .map((cliente -> this.toDTO(cliente)
                )).collect(Collectors.toList());
        return clienteResponseList;
    }


}
