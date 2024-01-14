package com.rogerioreis.desafio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.ZonedDateTime;

@Entity
@EntityListeners(ClientRecord.class)
@Table(name = "client")
public record ClientRecord(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,

        @Column(name = "nome", length = 200, nullable = false)
        @NotBlank(message = "O campo nome é de preenchimento obrigatório")
        @NotNull(message = "O campo nome não pode ser vazio.")
        @Length(min = 2, max = 200)
        String name,

        @Column(name = "email", length = 200, nullable = false)
        @NotBlank(message = "O campo email não pode ser vazio.")
        @NotNull(message = "O campo email é de preenchimento obrigatório")
        @Email(message = "Informe um email válido")
        @Length(min = 7, max = 200)
        String email,

        @Column(name = "data_cadastro", nullable = false)
        @Future(message = "Data de cadastro inválida")
        ZonedDateTime dateInit,

        @Column(name = "data_fim", nullable = true)
        ZonedDateTime dateEnd
) {
    public ClientRecord {
        dateInit = ZonedDateTime.now();
    }
}
