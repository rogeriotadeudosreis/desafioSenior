package com.rogerioreis.desafio.model;

import com.rogerioreis.desafio.enuns.EnumNacionalidade;
import com.rogerioreis.desafio.enuns.EnumSexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"pessoa"})
@Entity(name = "PESSOA_FISICA")
@Schema(name = "Pessoa física", description = "Cadastro de uma pessoa física.")
public class PessoaFisica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificado da pessoa física.")
    private Long id;

    @Getter
    @Setter
    @Column(name = "NOME", length = 200, nullable = false)
    @Schema(description = "Informa o nome da pessoa física.")
    private String nome;

    @Getter
    @Setter
    @Column(name = "NOME_SOCIAL", length = 200)
    @Schema(description = "Informa o nome social da pessoa física.")
    private String nomeSocial;

    @Getter
    @Setter
    @Column(name = "CPF", length = 11, nullable = false, unique = false)
    @Schema(description = "Informa o cpf da pessoa física.")
    private String cpf;

    @Getter
    @Setter
    @Column(name = "RG", length = 20, unique = false)
    @Schema(description = "Informa o RG da pessoa física.")
    private String rg;

    @Getter
    @Setter
    @Column(name = "PASSAPORTE", length = 10, unique = true)
    @Schema(description = "Informa o passaporte caso a pessoa for estrangeira.")
    private String passaporte;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "SEXO", length = 1, nullable = false)
    @Schema(description = "Informa o sexo da pessoa.")
    private EnumSexo sexo;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "NACIONALIDADE", length = 11, nullable = false)
    @Schema(description = "Informa a nacionalidade de uma pessoa.")
    private EnumNacionalidade nacionalidade;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_PESSOA", nullable = false, referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_PESSOA"))
    @Schema(name = "Pessoa")
    private Pessoa pessoa;


    @Getter
    @Setter
    @Column(name = "DATA_CADASTRO", nullable = false, updatable = false)
    @Schema(description = "Data de cadastro.")
    private ZonedDateTime dataCadastro;

    @Getter
    @Setter
    @Column(name = "DATA_ATUALIZACAO")
    @Schema(name = "Data de atualização.")
    private ZonedDateTime dataAtualizacao;

    @Transient
    @Getter
    @Setter
    private Contato contato;

    public PessoaFisica(PessoaFisica pessoaFisica) {
        this.nome = pessoaFisica.getNome();
        this.nomeSocial = pessoaFisica.getNomeSocial();
        this.cpf = pessoaFisica.getCpf();
        this.rg = pessoaFisica.getRg();
        this.passaporte = pessoaFisica.getPassaporte();
        this.sexo = pessoaFisica.getSexo();
        this.nacionalidade = pessoaFisica.getNacionalidade();
        this.pessoa = pessoaFisica.getPessoa();
        this.dataCadastro = pessoaFisica.getDataCadastro();
        this.dataAtualizacao = pessoaFisica.getDataAtualizacao();
        this.contato = pessoaFisica.getContato();
    }

    @PrePersist
    private void prePersist() {
        this.dataCadastro = ZonedDateTime.now();
    }

    @PreUpdate
    private void updatePersist() {
        this.dataAtualizacao = ZonedDateTime.now();
    }

}
