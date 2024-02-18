package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.PessoaFisicaRequest;
import com.rogerioreis.desafio.dto.PessoaFisicaResponse;
import com.rogerioreis.desafio.model.PessoaFisica;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PessoaFisicaMapper {

    public PessoaFisicaResponse toDTO(PessoaFisica pessoaFisica) {
        if (pessoaFisica != null) {

            return new PessoaFisicaResponse(
                    pessoaFisica.getId(),
                    pessoaFisica.getNome(),
                    pessoaFisica.getNomeSocial(),
                    pessoaFisica.getSexo(),
                    pessoaFisica.getNacionalidade(),
                    pessoaFisica.getDataNascimento()
            );
        }
        return null;
    }

    public PessoaFisica toEntity(PessoaFisicaRequest pessoaFisicaRequest) {
        return new PessoaFisica(
                pessoaFisicaRequest.id(),
                pessoaFisicaRequest.nome(),
                pessoaFisicaRequest.nomeSocial(),
                pessoaFisicaRequest.cpf(),
                pessoaFisicaRequest.rg(),
                pessoaFisicaRequest.passaporte(),
                pessoaFisicaRequest.sexo(),
                pessoaFisicaRequest.nacionalidade(),
                pessoaFisicaRequest.dataNascimento(),
                pessoaFisicaRequest.cliente());
    }

    public List<PessoaFisicaResponse> toListDTO(List<PessoaFisica> pessoaFisicaList) {
        List<PessoaFisicaResponse> responseList = pessoaFisicaList.stream()
                .map((pessoaFisica) ->
                        new PessoaFisicaResponse(
                                pessoaFisica.getId(),
                                pessoaFisica.getNome(),
                                pessoaFisica.getNomeSocial(),
                                pessoaFisica.getSexo(),
                                pessoaFisica.getNacionalidade(),
                                pessoaFisica.getDataNascimento()))
                .collect(Collectors.toList());

        return responseList;
    }
}
