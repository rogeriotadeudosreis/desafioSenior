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

    @NotBlank(message = "O campo nome é obrigatório.")
    @Length(min = 3, message = "O limite mínimo do campo NOME do cliente é de 03 caracteres.")
    @Length(max = 200, message = "O limite do campo NOME do cliente é de 200 caracteres.")
    private String nome;

    @NotBlank(message = "O campo email é obrigatório.")
    @Length(max = 200, message = "O limite do campo EMAIL é de 200 caracteres.")
    @Email(message = "O email está no formato inválido.")
    private String email;

    private ZonedDateTime dataInicio;

    private ZonedDateTime dataFim;

    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }

}
