package com.rogerioreis.desafio.enun;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumTipoTelefone {

    MOVEL("Móvel"),
    PROFISSIONAL("Profissional"),
    RESIDENCIAL("Residencial");


    @Getter
    private final String descricao;

    @Override
    public String toString() {
        return descricao;
    }

}
