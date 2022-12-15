package com.rogerioreis.desafio.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumSituacaoPedido {

    ABERTO("ABERTO"),

    FECHADO("FECHADO");

    @Getter
    private final String descricao;

    @Override
    public String toString() {
        return this.descricao;
    }
}
