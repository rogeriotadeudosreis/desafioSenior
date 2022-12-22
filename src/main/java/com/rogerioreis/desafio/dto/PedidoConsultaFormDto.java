package com.rogerioreis.desafio.dto;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rogerioreis.desafio.enuns.EnumSituacaoPedido;
import com.rogerioreis.desafio.model.Cliente;
import com.rogerioreis.desafio.model.Item;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PedidoConsultaFormDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String numeroPedido;

    private ZonedDateTime dataInicio;

    private ClienteConsultaDto cliente;

    private List<ItemConsultaFormDto> itens = new ArrayList<>();

    private double subTotalPedido;

    private double desconto;

    private double totalPedido;

    private EnumSituacaoPedido situacao;

    @JsonIgnore
    private ZonedDateTime dataFim;

    @JsonGetter
    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }


}
