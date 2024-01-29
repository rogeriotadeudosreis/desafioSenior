package com.rogerioreis.desafio.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EnumCategoria {
    ELETRONICO("notebook, teclado, mouse, dispositivos de áudio"),
    AUTOMOTIVO("peças, acessórios"),
    LITERATURA("livros, documentários, biografias");

    @Getter
    private final String descricao;

    @Override
    public String toString() {
        return getDescricao();
    }
}
