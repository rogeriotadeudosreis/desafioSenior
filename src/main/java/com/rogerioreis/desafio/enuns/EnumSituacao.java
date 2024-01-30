package com.rogerioreis.desafio.enuns;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EnumSituacao {

    ATIVO("ativo"),
    INATIVO("inativo");

    private final String descricao;

    @Override
    public String toString() {
        return descricao;
    }
}
