package com.rogerioreis.desafio.enun;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum EnumSexo {

    F("Feminino"),
    M("Masculino");

    @Getter
    private String sigla;


}
