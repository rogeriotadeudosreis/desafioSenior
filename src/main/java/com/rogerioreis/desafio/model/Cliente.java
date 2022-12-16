package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rogerioreis.desafio.dto.ClienteFormDto;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidoList = new ArrayList<>();

    @PrePersist
    private void init() {
        this.dataInicio = ZonedDateTime.now();
    }

    @JsonGetter
    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }

}
