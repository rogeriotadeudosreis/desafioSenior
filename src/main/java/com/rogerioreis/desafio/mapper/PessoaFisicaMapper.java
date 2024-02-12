package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.PessoaFisicaRequest;
import com.rogerioreis.desafio.dto.PessoaFisicaResponse;
import com.rogerioreis.desafio.model.PessoaFisica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaFisicaMapper {

    @Autowired
    private PessoaMapper pessoaMapper;

    public PessoaFisicaResponse toDTO(PessoaFisica pessoaFisica) {
        return new PessoaFisicaResponse(
                pessoaFisica.getId(),
                pessoaFisica.getNome(),
                pessoaFisica.getNomeSocial(),
                pessoaFisica.getSexo(),
                pessoaFisica.getNacionalidade(),
                pessoaFisica.getDataNascimento(),
                pessoaMapper.ToDTO(pessoaFisica.getPessoa()));

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
                pessoaFisicaRequest.pessoa());
    }
}
