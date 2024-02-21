package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.TelefoneRequest;
import com.rogerioreis.desafio.dto.TelefoneResponse;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.mapper.TelefonelMapper;
import com.rogerioreis.desafio.model.Contato;
import com.rogerioreis.desafio.model.Telefone;
import com.rogerioreis.desafio.repositories.TelefoneRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private TelefonelMapper telefonelMapper;

    public TelefoneResponse create(TelefoneRequest telefoneRequest) {
        Telefone telefone = telefonelMapper.toEntity(telefoneRequest);
        telefone.setId(null);
        TelefoneResponse telefoneResponse = telefonelMapper.toDTO(telefoneRepository.save(telefone));
        return telefoneResponse;
    }

    public List<TelefoneResponse> findAllTelefonesResponsesByContatoId(Long idContato) {
        if (idContato != null) {
            throw new RegraNegocioException("É necessário informar o id do contato para esta consulta.");
        }
        List<Telefone> telefones = telefoneRepository.findAllByContatoId(idContato);
        if (!telefones.isEmpty()) {
            return telefonelMapper.toListDTO(telefones);
        } else {
            throw new RecursoNaoEncontradoException("não foi possível encontrar emails para este id de contato");
        }
    }

    public List<Telefone> findAllTelefonesByContatoId(Long contatoId) {
        if (contatoId == null) {
            throw new RegraNegocioException("É necessário informar o id do contato para esta consulta.'");
        }
        List<Telefone> telefoneList = telefoneRepository.findAllByContatoId(contatoId);
        if (!telefoneList.isEmpty()) {
            return telefoneList;
        }
        return null;
    }

    public List<TelefoneResponse> createTelefoneByContato(Contato contato, List<Telefone> telefones) {
        if (contato != null && telefones != null && !telefones.isEmpty()) {
            List<TelefoneResponse> listTelefonesResponses = new ArrayList<>();
            for (Telefone telefone : telefones) {
                if (StringUtils.isNotBlank(telefone.getTelefone())) {
                    Telefone telefoneSalvar = new Telefone(telefone.getId(), telefone.getTelefone(), telefone.getDdd(),
                            telefone.getDdi(), telefone.getTipoTelefone(),
                            null, null, null, contato);
                    listTelefonesResponses.add(telefonelMapper.toDTO(telefoneRepository.save(telefoneSalvar)));
                } else {
                    throw new RegraNegocioException("É necessário informar pelo menos 01(um) telefone válido.");
                }
            }
            return listTelefonesResponses;
        } else {
            throw new RegraNegocioException("A lista de telefones está nula ou vazia.");
        }
    }

    public Telefone findTelefoneById(Long id) {
        if (id == null) {
            throw new RegraNegocioException("É preciso informar um id para consultar o registro de um telefone.");
        }
        Telefone telefone = telefoneRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de telefone com o id [" + id + "] não encontrado"));
        return telefone;
    }

    public TelefoneResponse findTelefoneResponseById(Long id) {
        if (id == null) {
            throw new RegraNegocioException("É preciso informar um id para consultar um registro de telefone.");
        }
        Telefone telefone = telefoneRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de telefone com o id [" + id + "] não encontrado"));
        TelefoneResponse telefoneResponse = telefonelMapper.toDTO(telefone);
        return telefoneResponse;
    }

    public void deleteById(TelefoneRequest telefoneRequest) {
        if (telefoneRequest == null) {
            throw new RegraNegocioException("É preciso informar um telefone para exclusão.");
        }
        Telefone telefone = this.findTelefoneById(telefoneRequest.id());
        telefoneRepository.delete(telefone);
    }

    public List<TelefoneResponse> createTelefones(List<TelefoneRequest> telefoneRequests) {
        if (telefoneRequests != null && !telefoneRequests.isEmpty()) {
            List<TelefoneResponse> telefoneResponses = new ArrayList<>();
            for (TelefoneRequest telefoneRequest : telefoneRequests) {
                Contato contato = telefoneRequest.contato();
                if (StringUtils.isNotBlank(telefoneRequest.telefone()) && StringUtils.isNotBlank(telefoneRequest.ddi())
                        && StringUtils.isNotBlank(telefoneRequest.ddd())) {
                    Telefone telefone = new Telefone(telefoneRequest.id(), telefoneRequest.telefone(), telefoneRequest.ddd(), telefoneRequest.ddi(), telefoneRequest.tipoTelefone(),
                            null, null, null, contato);
                    telefoneResponses.add(telefonelMapper.toDTO(telefoneRepository.save(telefone)));
                } else {
                    throw new RegraNegocioException("É necessário informar o DDI, DDD e número do telefone para salvar.");
                }
            }
            return telefoneResponses;
        } else {
            throw new RegraNegocioException("A lista de telefones está nula ou vazia.");
        }

    }

}
