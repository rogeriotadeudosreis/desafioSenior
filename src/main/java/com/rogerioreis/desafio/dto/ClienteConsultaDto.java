package com.rogerioreis.desafio.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ClienteConsultaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    private String email;

    private boolean isAtivo;

    public ClienteConsultaDto(ClienteConsultaDto dto) {
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.isAtivo = dto.isAtivo();
    }
}
