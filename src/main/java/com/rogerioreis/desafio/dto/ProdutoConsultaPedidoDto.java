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
public class ProdutoConsultaPedidoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    private String codigo;

    private EnumTipoProduto tipoProduto;

}
