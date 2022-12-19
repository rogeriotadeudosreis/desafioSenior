package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
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
    private String nome;

    @Column(name = "EMAIL", length = 200, nullable = false)
    private String email;

    @Column(name = "DATA_INICIO", nullable = false, updatable = false)
    private ZonedDateTime dataInicio;

    @Column(name = "DATA_FIM")
    private ZonedDateTime dataFim;

    @Column(name = "DATA_ATUALIZACAO")
    @ApiModelProperty(value = "Data de atualização do cadastro do cliente")
    private ZonedDateTime dataAtualizacao;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidoList = new ArrayList<>();

    public Cliente(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    @PrePersist
    private void init() {
        this.dataInicio = ZonedDateTime.now();
    }

    @PreUpdate
    private void update() {
        this.dataAtualizacao = ZonedDateTime.now();
    }

    @JsonGetter
    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }

}
