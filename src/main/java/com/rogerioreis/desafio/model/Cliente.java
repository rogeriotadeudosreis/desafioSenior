package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", length = 200, nullable = false)
    @NotBlank(message = "O campo nome é obrigatório.")
    @Length(max = 200, message = "O limite do campo NOME do cliente é de 200 caracteres.")
    @Length(min = 3, message = "O limite mínimo do campo NOME do cliente é de 03 caracteres.")
    private String nome;

    @Column(name = "EMAIL", length = 200, nullable = false)
    @NotBlank(message = "O campo email é obrigatório.")
    @Length(max = 200, message = "O limite do campo EMAIL é de 200 caracteres.")
    @Email(message = "O Email fornecido é inválido.")
    private String email;

    @Column(name = "DATA_INICIO")
    private ZonedDateTime dataInicio;


    @Column(name = "DATA_FIM")
    private ZonedDateTime dataFim;

    @PrePersist
    private void init() {
        this.dataInicio = ZonedDateTime.now();
    }

    @JsonGetter
    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }


}
