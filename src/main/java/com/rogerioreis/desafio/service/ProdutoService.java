package com.rogerioreis.desafio.service;

import com.rogerioreis.desafio.exception.RecursoExistenteException;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.exception.RequisicaoComErroException;
import com.rogerioreis.desafio.model.Produto;
import com.rogerioreis.desafio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto create(Produto produto) {

        validaProduto(produto);

        Produto produtoSalvo = this.produtoRepository.save(produto);

        return produtoSalvo;

    }

    public Produto readById(Long id) {

        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado."));

        return produto;

    }

    public Page<Produto> page(String descricao, Pageable pageable) {

        String desc = descricao != null ? descricao : "";

        return produtoRepository.findAllByNomeLikeIgnoreCaseOrCodigoIgnoreCase(desc, pageable);
    }

    public Produto update(Long id, Produto produtoForm) {

        Produto produto = this.readById(id);

        produtoForm.setId(produto.getId());
        produtoForm.setCodigo(produto.getCodigo());
        produtoForm.setDataInicio(produto.getDataInicio());
        produtoForm.setDataFim(produto.getDataFim());

        return produtoRepository.save(produtoForm);

    }

    public void delete(Long id) {

        Produto produto = readById(id);

        produto.setDataFim(ZonedDateTime.now());

        this.produtoRepository.save(produto);

    }

    public void validaProduto(Produto produto) {

        if (produto.getCodigo().isEmpty()) throw new RequisicaoComErroException("Código do produto nulo.");

        boolean isProdutoFind = produtoRepository.findProdutoByCodigoIgnoreCase(produto.getCodigo()).isPresent();

        if (isProdutoFind) {
            throw new RecursoExistenteException("Já existe um produto cadastrado com o código informado.");
        }

        if (produto.getPreco() <= 0) {
            throw new RegraNegocioException("O valor do produto não pode ser menor ou igual a zero");
        }
    }
}
