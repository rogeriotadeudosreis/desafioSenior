package com.rogerioreis.desafio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ClienteFormDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Length(max = 200, message = "O limite do campo NOME do cliente é de 200 caracteres.")
    private String nome;

    private String email;

    private ZonedDateTime dataInicio;

    @JsonIgnore
    private ZonedDateTime dataFim;

    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }

}
