package com.rogerioreis.desafio.mapper;

import com.rogerioreis.desafio.dto.PessoaFisicaRequest;
import com.rogerioreis.desafio.dto.PessoaFisicaResponse;
import com.rogerioreis.desafio.model.Email;
import com.rogerioreis.desafio.model.PessoaFisica;
import com.rogerioreis.desafio.model.Telefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PessoaFisicaMapper {

    @Autowired
    private PessoaMapper mapper;

    public PessoaFisicaResponse toDTO(PessoaFisica pessoaFisica, Set<Email> emails, Set<Telefone> telefones) {
        return new PessoaFisicaResponse(
                pessoaFisica.getNome(),
                pessoaFisica.getNomeSocial(),
                pessoaFisica.getSexo(),
                pessoaFisica.getNacionalidade(),
                mapper.ToDTO(pessoaFisica.getPessoa()),
                emails,
                telefones
        );
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
                pessoaFisicaRequest.pessoa(),
                pessoaFisicaRequest.dataCadastro(),
                pessoaFisicaRequest.dataAtualizacao());
    }
}
