package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class ItensPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "QUANTIDADE")
    @NotBlank(message = "O campo quantidade é obrigatório.")
    @Min(value = 1, message = "Informe pelo menos um item ao pedido.")
    private Long quantidade;

    @Column(name = "PRECO", nullable = false)
    @NotBlank(message = "O campo PREÇO é obrigatório.")
    @Digits(integer = 9, fraction = 2)
    private double price;

    @Column(name = "DESCONTO")
    @Digits(integer = 9, fraction = 2)
    private double desconto;

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
