package com.rogerioreis.desafio.model;

import com.rogerioreis.desafio.enuns.EnumTipoEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.internal.build.AllowNonPortable;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllowNonPortable
@NoArgsConstructor
@Table(name = "ENDERECO")
@Schema(description = "Armazena um endereço de cliente pessoa física ou jurídica.")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_ENDERECO")
    private EnumTipoEndereco tipoEndereco;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco endereco)) return false;
        return getId().equals(endereco.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
