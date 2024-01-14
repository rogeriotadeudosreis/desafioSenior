package com.rogerioreis.desafio.dto;

import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductOrderResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    private String codigo;

    private EnumTipoProduto tipoProduto;

}
