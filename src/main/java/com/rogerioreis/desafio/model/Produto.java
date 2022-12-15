package com.rogerioreis.desafio.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.rogerioreis.desafio.enuns.EnumTipoProduto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "PRODUTO")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", length = 200, nullable = false)
    @NotBlank(message = "O campo NOME é obrigatório.")
    @Length(max = 200, message = "O limite do campo NOME do produto é de 200 caracteres.")
    @Length(min = 3, message = "O limite mínimo do campo NOME do produto é de 03 caracteres.")
    @ApiModelProperty(value = "Descrição do produto")
    private String nome;

    @Column(name = "CODIGO", length = 200, nullable = false)
    @NotBlank(message = "O campo CODIGO é obrigatório.")
    @Length(max = 200, message = "O limite do campo CODIGO do produto é de 200 caracteres.")
    @Length(min = 3, message = "O limite mínimo do campo CODIGO do produto é de 03 caracteres.")
    private String codigo;

    @Column(name = "PRECO", nullable = false)
    @Digits(integer = 9, fraction = 2)
    @NotNull(message = "Informe o valor do produto ou serviço.")
    private double preco;

    @Column(name = "DATA_INICIO", nullable = false, updatable = false)
    private ZonedDateTime dataInicio;

    @Column(name = "DATA_FIM")
    private ZonedDateTime dataFim;

    @NotNull(message = "O tipo de produto é obrigatório.")
    @Column(name = "TIPO_PRODUTO",length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumTipoProduto tipoProduto;

    @PrePersist
    private void init() {
        this.dataInicio = ZonedDateTime.now();
    }

    @JsonGetter
    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(ZonedDateTime.now()) > 0;
    }

}
