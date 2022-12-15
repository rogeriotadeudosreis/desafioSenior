package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.Pedido;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    private String nome;

    private String email;

    private ZonedDateTime dataFim;

    private List<Pedido> pedidoList = new ArrayList<>();

    public boolean isAtivo(){
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }

    public ClienteDto(Cliente clinte) {
    }


}
