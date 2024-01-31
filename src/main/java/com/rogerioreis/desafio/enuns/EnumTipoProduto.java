package com.rogerioreis.desafio.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumTipoProduto {

    PRODUTO("Produto"),

    SERVICO("Serviço");

    @Getter
    private final String descricao;

    @Override
    public String toString() {
        return this.descricao;
    }
}
