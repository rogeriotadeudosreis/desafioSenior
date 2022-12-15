package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.model.Cliente;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFormDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String email;

    private ZonedDateTime dataFim;

    public ClienteFormDto(Cliente cliente) {
    }

}
