package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import com.rogerioreis.desafio.model.Produto;
import lombok.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProdutoFormDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String codigo;

    private double preco;

    private ZonedDateTime dataFim;

    private EnumTipoProduto tipoProduto;

    public ProdutoFormDto (Produto produto){

    }




}
