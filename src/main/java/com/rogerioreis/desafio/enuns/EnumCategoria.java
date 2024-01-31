package com.rogerioreis.desafio.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumCategoria {
    ELETRONICO("Eletronico"),
    AUTOMOTIVO("Automotivo"),
    LITERATURA("Literatura");

    @Getter
    private final String descricao;

    @Override
    public String toString() {
        return getDescricao();
    }
}
