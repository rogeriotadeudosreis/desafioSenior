package com.rogerioreis.desafio.enun;

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
