package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumTipoTelefone;
import com.rogerioreis.desafio.model.Contato;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record TelefoneResponse(
        Long id,
        String telefone,
        String ddd,
        String ddi,
        EnumTipoTelefone tipoTelefone
) {

}
