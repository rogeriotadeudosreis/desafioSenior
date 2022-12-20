package com.rogerioreis.desafio.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
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

    @NotBlank(message = "{nome.not.blank}")
    @Length(min = 3, message = "{name.length.min}")
    @Length(max = 200, message = "{name.length.max}")
    private String nome;

    @NotBlank(message = "{email.not.blank}")
    @Length(max = 200, message = "{email.length.min}")
    @Email(message = "{email.not.valid}")
    private String email;

    private ZonedDateTime dataInicio;

    @JsonIgnore
    private ZonedDateTime dataFim;

    @JsonGetter
    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }

}
