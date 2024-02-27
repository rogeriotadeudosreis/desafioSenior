package com.rogerioreis.desafio.projection;

import com.rogerioreis.desafio.enun.EnumTipoEndereco;

public interface EnderecoProjetction {

    String getCep();

    String getEstado();

    String getLocalidade();

    String getLogradouro();

    String getBairro();

    String getNumero();

    String getComplemento();

    String getTipoEndereco();

}
