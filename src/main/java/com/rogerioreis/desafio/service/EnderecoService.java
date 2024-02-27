package com.rogerioreis.desafio.service;

import com.rogerioreis.desafio.dto.EnderecoRequest;
import com.rogerioreis.desafio.dto.EnderecoResponse;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.mapper.EnderecoMapper;
import com.rogerioreis.desafio.model.Contato;
import com.rogerioreis.desafio.model.Endereco;
import com.rogerioreis.desafio.repository.EnderecoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    public EnderecoResponse create(EnderecoRequest enderecoRequest) {
        Endereco endereco = enderecoMapper.toEntity(enderecoRequest);
        endereco.setId(null);
        EnderecoResponse enderecoResponse = enderecoMapper.toDTO(enderecoRepository.save(endereco));
        return enderecoResponse;
    }

    public List<EnderecoResponse> findAllEnderecosResponseByContatoId(Long contatoId) {
        if (contatoId == null) {
            throw new RegraNegocioException("É necessário informar o id de um contato para consultar lista de endereços.");
        }
        List<Endereco> enderecos = enderecoRepository.findAllByContatoId(contatoId);
        if (!enderecos.isEmpty()) {
            List<EnderecoResponse> enderecoResponses = enderecoMapper.toListDTO(enderecos);
            return enderecoResponses;
        }
        return null;
    }

    public List<Endereco> findAllEnderecosByContatoId(Long contatoId) {
        if (contatoId == null) {
            throw new RegraNegocioException("É necessário informar o id de um contato para consultar lista de endereços.");
        }
        List<Endereco> enderecos = enderecoRepository.findAllByContatoId(contatoId);
        if (!enderecos.isEmpty()) {
            return enderecos;
        }
        return null;
    }

    public List<EnderecoResponse> createEnderecoByContato(Contato contato, List<Endereco> enderecos) {
        if (contato != null && enderecos != null && !enderecos.isEmpty()) {
            List<EnderecoResponse> enderecoResponses = new ArrayList<>();
            for (Endereco endereco : enderecos) {
                if (!enderecos.isEmpty()) {
                    Endereco enderecoSalvar = new Endereco(endereco.getId(), endereco.getEstado(), endereco.getLocalidade(),
                            endereco.getCep(), endereco.getBairro(), endereco.getLogradouro(), endereco.getNumero(),
                            endereco.getComplemento(), endereco.getTipoEndereco(), endereco.getDataInicio(),
                            endereco.getDataFim(), endereco.getDataAtualizacao(), contato);
                    enderecoResponses.add(enderecoMapper.toDTO(enderecoRepository.save(enderecoSalvar)));
                } else {
                    throw new RegraNegocioException("É necessário informar pelo menos 01(um) endereco válido.");
                }
            }
            return enderecoResponses;
        } else {
            throw new RegraNegocioException("A lista de enderecos está nula ou vazia.");
        }
    }

    public Endereco findEnderecoById(Long id) {
        if (id == null) {
            throw new RegraNegocioException("É necessário informar o id para consultar um endereço.");
        }
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de endereço com o id [" + id + "] não encontrado"));
        return endereco;
    }

    public EnderecoResponse findEnderecoresponseById(Long id) {
        if (id == null) {
            throw new RegraNegocioException("É necessário informar o id para consultar um endereço.");
        }
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de endereço com o id [" + id + "] não encontrado"));
        return enderecoMapper.toDTO(endereco);
    }

    public void deleteByEndereco(EnderecoRequest enderecoRequest) {
        if (enderecoRequest == null) {
            throw new RegraNegocioException("É preciso informar um endereço para excluir.");
        }
        Endereco endereco = this.findEnderecoById(enderecoRequest.id());
        enderecoRepository.delete(endereco);
    }

    public List<EnderecoResponse> createEnderecos(List<EnderecoRequest> enderecoRequests) {
        if (!enderecoRequests.isEmpty()) {
            List<EnderecoResponse> enderecoResponses = new ArrayList<>();
            for (EnderecoRequest request : enderecoRequests) {
                if (StringUtils.isNotBlank(request.estado()) &&
                        StringUtils.isNotBlank(request.localidade()) &&
                        StringUtils.isNotBlank(request.bairro()) &&
                        StringUtils.isNotBlank(request.logradouro()) &&
                        StringUtils.isNotBlank(request.numero()) &&
                        StringUtils.isNotBlank(request.cep())) {
                    Endereco endereco = new Endereco(
                            request.id(), request.estado(), request.localidade(), request.cep(), request.bairro(),
                            request.logradouro(), request.numero(), request.complemento(), request.tipoEndereco(),
                            null, null, null, request.contato());
                    enderecoResponses.add(enderecoMapper.toDTO(enderecoRepository.save(endereco)));
                } else {
                    throw new RegraNegocioException("Algum ou todos objetos da lista não contém atributos obrigatórios.");
                }
            }
            return enderecoResponses;

        } else {
            throw new RegraNegocioException("A lista de endereço está nula ou vazia.");
        }
    }


}







