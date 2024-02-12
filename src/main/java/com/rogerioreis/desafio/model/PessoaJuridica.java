package com.rogerioreis.desafio.model;

import com.rogerioreis.desafio.dto.PessoaJuridicaRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PESSOA_JURIDICA")
public class PessoaJuridica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador da pessoa jurídica.")
    private Long id;

    @Getter
    @Setter
    @Column(name = "RAZAO_SOCIAL", length = 200, nullable = false)
    @Schema(description = "Informa a razão social da empresa.")
    private String razaoSocial;

    @Getter
    @Setter
    @Column(name = "NOME_FANTASIA", length = 200)
    @Schema(description = "Informa o nome fantasia da empresa.")
    private String nomeFantasia;

    @Getter
    @Setter
    @Column(name = "CNPJ", length = 14, nullable = false)
    @Schema(description = "Informa o cnpj da empresa.")
    private String cnpj;

    @Getter
    @Setter
    @Column(name = "INSCRICAO_ESTADUAL", length = 30)
    @Schema(description = "Informa a inscrição estdual da empresa.")
    private String inscricaoEstadual;

    @Getter
    @Setter
    @Column(name = "INSCRICAO_MUNICIPAL", length = 30)
    @Schema(description = "Informa da inscrição municipal da empresa.")
    private String inscricaoMunicipal;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_PESSOA", referencedColumnName = "ID", updatable = false,
            foreignKey = @ForeignKey(name = "FK_PESSOA"))
    private Pessoa pessoa;

    public PessoaJuridica(PessoaJuridicaRequest pessoaJuridicaRequest) {

    }
}
