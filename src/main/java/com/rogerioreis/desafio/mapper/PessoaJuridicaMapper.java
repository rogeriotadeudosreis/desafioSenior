package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.PessoaJuridicaRequest;
import com.rogerioreis.desafio.dto.PessoaJuridicaResponse;
import com.rogerioreis.desafio.model.PessoaJuridica;
import org.springframework.stereotype.Component;

@Component
public class PessoaJuridicaMapper {

    public PessoaJuridica toEntity(PessoaJuridicaRequest juridicaRequest) {
        return new PessoaJuridica(
                juridicaRequest.id(),
                juridicaRequest.razaoSocial(),
                juridicaRequest.nomeFantasia(),
                juridicaRequest.cnpj(),
                juridicaRequest.inscricaoEstadual(),
                juridicaRequest.inscricaoMunicipal(),
                juridicaRequest.cliente()
        );
    }

    public PessoaJuridicaResponse toDTO(PessoaJuridica pessoaJuridica) {
        return new PessoaJuridicaResponse(pessoaJuridica.getId(), pessoaJuridica.getRazaoSocial(), pessoaJuridica.getNomeFantasia(),
                pessoaJuridica.getCnpj());
    }

//    public List<PessoaJuridicaResponse> toListDTO(List<PessoaJuridica> pessoaFisicaList) {
//        List<PessoaJuridicaResponse> responseList = pessoaFisicaList.stream()
//                .map((pessoaFisica) ->
//                        new PessoaJuridicaResponse(
//                                pessoaFisica.getId(),
//                                pessoaFisica.getNome(),
//                                pessoaFisica.getNomeSocial(),
//                                pessoaFisica.getSexo(),
//                                pessoaFisica.getNacionalidade(),
//                                pessoaFisica.getDataNascimento()))
//                .collect(Collectors.toList());
//
//        return responseList;
//    }
}
