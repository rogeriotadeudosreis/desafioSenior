package com.rogerioreis.desafio.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProdutoConsultaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    private String codigo;

    private double preco;

    private ZonedDateTime dataAtualizacao;

    private EnumTipoProduto tipoProduto;

    @JsonIgnore
    private ZonedDateTime dataFim;

    @JsonGetter
    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }


}
