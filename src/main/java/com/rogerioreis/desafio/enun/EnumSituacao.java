package com.rogerioreis.desafio.enun;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EnumSituacao {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;

    @Override
    public String toString() {
        return descricao;
    }
}
