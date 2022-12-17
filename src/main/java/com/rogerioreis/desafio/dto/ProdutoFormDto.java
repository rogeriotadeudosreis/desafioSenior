package com.rogerioreis.desafio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import com.rogerioreis.desafio.model.Produto;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Enumerated;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProdutoFormDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "O campo NOME é obrigatório.")
    @Length(max = 200, message = "O limite do campo NOME do produto é de 200 caracteres.")
    @Length(min = 3, message = "O limite mínimo do campo NOME do produto é de 03 caracteres.")
    private String nome;

    @NotBlank(message = "O campo CODIGO é obrigatório.")
    @Length(max = 200, message = "O limite do campo CODIGO do produto é de 200 caracteres.")
    @Length(min = 3, message = "O limite mínimo do campo CODIGO do produto é de 03 caracteres.")
    private String codigo;

    @Digits(integer = 9, fraction = 2)
    @NotNull(message = "Informe o valor do produto ou serviço.")
    private double preco;

    private EnumTipoProduto tipoProduto;

    @JsonIgnore
    private ZonedDateTime dataFim;

    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }

    public ProdutoFormDto (Produto produto){

    }




}
