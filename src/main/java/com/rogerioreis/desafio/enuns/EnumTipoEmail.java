package com.rogerioreis.desafio.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumTipoEmail {

    PRINCIPAL("Principal"),
    SECUNDARIO("Secundario");

    @Getter
    private final String descricao;

    @Override
    public String toString() {
        return descricao;
    }
}
