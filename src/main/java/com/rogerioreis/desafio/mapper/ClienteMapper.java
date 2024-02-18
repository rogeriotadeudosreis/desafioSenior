package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.ClienteResponse;
import com.rogerioreis.desafio.model.Cliente;
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
        List<ClienteResponse> clienteResponseList = clienteList.stream()
                .map((cliente ->
                        new ClienteResponse(
                                cliente.getId(), cliente.getSituacao(),
                                contatoMapper.toDTO(cliente.getContato()),
                                pessoaFisicaMapper.toDTO(cliente.getPessoaFisica()),
                                pessoaJuridicaMapper.toDTO(cliente.getPessoaJuridica())
                        )
                )).collect(Collectors.toList());
        return clienteResponseList;
    }


}
