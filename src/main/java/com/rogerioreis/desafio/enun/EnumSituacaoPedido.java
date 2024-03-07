package com.rogerioreis.desafio.enun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumSituacaoPedido {

    ABERTO("Aberto"),

    FECHADO("Fechado"),

    PAGO("Pago");

    @Getter
    private final String descricao;

    @Override
    public String toString() {
        return this.descricao;
    }
}
