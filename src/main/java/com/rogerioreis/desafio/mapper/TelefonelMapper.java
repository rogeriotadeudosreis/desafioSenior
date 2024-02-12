package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.TelefoneResponse;
import com.rogerioreis.desafio.model.Telefone;
import org.springframework.stereotype.Component;

@Component
public class TelefonelMapper {

    public TelefoneResponse toDTO(Telefone telefone) {
        return new TelefoneResponse(
                telefone.getTelefone(),
                telefone.getDdd(),
                telefone.getDdi(),
                telefone.getTipoTelefone());
    }


}
