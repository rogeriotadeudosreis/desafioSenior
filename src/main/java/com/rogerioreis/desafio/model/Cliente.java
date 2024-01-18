package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", length = 200, nullable = false)
    @NotBlank(message = "{nome.not.blank}")
    @Length(min = 3, message = "{name.length.min}")
    @Length(max = 200, message = "{name.length.max}")
    private String nome;

    @Column(name = "EMAIL", length = 200, nullable = false)
    @NotBlank(message = "{email.not.blank}")
    @Length(max = 200, message = "{email.length.min}")
    @Email(message = "{email.not.valid}")
    private String email;

    @Column(name = "INICIO_VIGENCIA", nullable = false, updatable = false)
    private ZonedDateTime inicioVigencia;

    @Column(name = "FIM_VIGENCIA")
    private ZonedDateTime fimVigencia;

    @Column(name = "ATUALIZACAO")
    @Schema(description= "Data de atualização do cadastro do cliente")
    private ZonedDateTime atualizacao;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Order> pedidoList = new ArrayList<>();

    public Cliente(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    @PrePersist
    private void init() {
        this.inicioVigencia = ZonedDateTime.now();
    }

    @PreUpdate
    private void update() {
        this.atualizacao = ZonedDateTime.now();
    }

    @JsonGetter
    public boolean isAtivo() {
        return getFimVigencia() == null || getFimVigencia().compareTo(ZonedDateTime.now()) > 0;
    }

}
