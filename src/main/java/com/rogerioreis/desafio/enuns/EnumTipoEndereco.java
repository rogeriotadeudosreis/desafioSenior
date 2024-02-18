package com.rogerioreis.desafio.enuns;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EnumTipoEndereco {

    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial"),
    COBRANCA("Cobrança"),
    OUTRO("outro");

    private final String descricao;

    @Override
    public String toString() {
        return descricao;
    }
}
