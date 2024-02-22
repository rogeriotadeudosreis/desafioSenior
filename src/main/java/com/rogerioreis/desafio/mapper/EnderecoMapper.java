package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.EnderecoRequest;
import com.rogerioreis.desafio.dto.EnderecoResponse;
import com.rogerioreis.desafio.model.Endereco;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnderecoMapper {

    public EnderecoResponse toDTO(Endereco endereco) {
        if (endereco != null) {

            return new EnderecoResponse(
                    endereco.getId(),
                    endereco.getEstado(),
                    endereco.getLocalidade(),
                    endereco.getCep(),
                    endereco.getBairro(),
                    endereco.getLogradouro(),
                    endereco.getNumero(),
                    endereco.getComplemento(),
                    endereco.getTipoEndereco().toString());
        }
        return null;
    }

    public List<EnderecoResponse> toListDTO(List<Endereco> enderecoList) {
        if (!enderecoList.isEmpty()) {
            List<EnderecoResponse> enderecoResponses = enderecoList.stream()
                    .map((endereco) -> this.toDTO(endereco)
                    ).collect(Collectors.toList());
            return enderecoResponses;
        }
        return null;
    }

    public EnderecoResponse toEntity(EnderecoRequest enderecoRequest) {
        if (enderecoRequest != null) {
            Endereco endereco = new Endereco(
                    enderecoRequest.id(),
                    enderecoRequest.estado(),
                    enderecoRequest.localidade(),
                    enderecoRequest.cep(),
                    enderecoRequest.bairro(),
                    enderecoRequest.logradouro(),
                    enderecoRequest.numero(),
                    enderecoRequest.complemento(),
                    enderecoRequest.tipoEndereco(),
                    null, null, null,
                    enderecoRequest.contato());
            EnderecoResponse response = this.toDTO(endereco);
        }
        return null;
    }
}
