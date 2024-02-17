package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.dto.PessoaResponse;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.mapper.PessoaMapper;
import com.rogerioreis.desafio.model.Pessoa;
import com.rogerioreis.desafio.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaMapper pessoaMapper;

    public void update(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            throw new RecursoNaoEncontradoException("É necessário informar o [id] da entidade [Pessoa]" +
                    "para atualizar");
        }
        Pessoa pessoaUpdate = pessoa;
        pessoaUpdate.setId(pessoa.getId());
        pessoaRepository.save(pessoaUpdate);
    }

    public PessoaResponse readById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de pessoa não encontro para o id: " + id));

        return pessoaMapper.toDTO(pessoa);
    }

    public List<PessoaResponse> readAll() {
        List<Pessoa> pessoaList = pessoaRepository.findAll();
        return pessoaMapper.toListDTO(pessoaList);
    }

    public Pessoa readPessoaById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de pessoa não encontro para o id: " + id));
    }
}
