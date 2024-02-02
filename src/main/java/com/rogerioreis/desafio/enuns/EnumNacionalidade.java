package com.rogerioreis.desafio.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumNacionalidade {

    BRASILEIRA("Brasileira"),
    ESTRANGEIRA("Estrangeira");

    @Getter
    private final String descricao;
}
