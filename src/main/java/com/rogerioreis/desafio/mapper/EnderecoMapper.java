package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.EnderecoResponse;
import com.rogerioreis.desafio.model.Endereco;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnderecoMapper {

    public EnderecoResponse toDTO(Endereco endereco) {
        return new EnderecoResponse(
                endereco.getId(),
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getCep(),
                endereco.getBairro(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getTipoEndereco().toString());
    }

    public List<EnderecoResponse> toListDTO(List<Endereco> enderecoList) {
        List<EnderecoResponse> enderecoResponses = enderecoList.stream()
                .map((endereco) -> this. toDTO(endereco)
                ).collect(Collectors.toList());
        return enderecoResponses;
    }
}
