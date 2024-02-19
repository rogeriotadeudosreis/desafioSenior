package com.rogerioreis.desafio.enuns;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumTipoCliente {

    PF("PF"),
    PJ("PJ");

    @Getter
    private String sigla;


}
