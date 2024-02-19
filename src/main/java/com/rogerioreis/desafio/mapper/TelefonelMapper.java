package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.TelefoneResponse;
import com.rogerioreis.desafio.model.Telefone;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TelefonelMapper {

    public TelefoneResponse toDTO(Telefone telefone) {
        return new TelefoneResponse(
                telefone.getId(),
                telefone.getTelefone(),
                telefone.getDdd(),
                telefone.getDdi(),
                telefone.getTipoTelefone());
    }

    public List<TelefoneResponse> toListDTO(List<Telefone> telefones) {
        List<TelefoneResponse> telefoneResponses = telefones.stream()
                .map((telefone -> toDTO(telefone)))
                .collect(Collectors.toList());
        return telefoneResponses;
    }


}
