package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.model.Cliente;
import lombok.*;
import org.springframework.data.domain.Page;

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

    public ClienteConsultaDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.isAtivo = cliente.isAtivo();
    }
}
