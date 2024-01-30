package com.rogerioreis.desafio.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PESSOA_FISICA")
public class PessoaFisica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificado da pessoa física.")
    private Long id;

    @OneToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID", updatable = false,
            foreignKey = @ForeignKey(name = "FK_PESSOA_PESSOA_FISICA"))
    private Pessoa pessoa;

    private String cpf;

    private String nome;

    private String nomeSocial;

    private String rg;




}
