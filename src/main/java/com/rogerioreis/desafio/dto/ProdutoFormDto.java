package com.rogerioreis.desafio.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import com.rogerioreis.desafio.model.Produto;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

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

    @NotBlank(message = "{name.not.blank}")
    @Length(min = 3, message = "{name.length.min}")
    @Length(max = 200, message = "{name.length.max}")
    private String nome;

    @NotBlank(message = "{code.not.blank}")
    @Length(min = 3, message = "{code.length.min}")
    @Length(max = 200, message = "{code.length.max}")
    private String codigo;

    @Digits(integer = 9, fraction = 2, message = "{price.not.valid}")
    @NotNull(message = "{price.not.null}")
    private double preco;

    @NotNull(message = "{type.product.not.null}")
    private EnumTipoProduto tipoProduto;

    private ZonedDateTime dataFim;

    @JsonGetter
    public boolean isAtivo() {
        return getPreco() > 0 && (getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0);
    }

    public ProdutoFormDto(ProdutoFormDto dto){
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.preco = dto.getPreco();
        this.codigo = dto.getCodigo();
        this.tipoProduto = dto.getTipoProduto();
        this.dataFim = dto.getDataFim();

    }

}
