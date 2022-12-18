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
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto create(Produto produto) {

        validaProduto(produto);

        return this.produtoRepository.save(produto);

    }

    public Produto readById(Long id) {

        if (id == null) {
            throw new RequisicaoComErroException("Id não informato");
        }

        return produtoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado."));

    }

    public Page<Produto> page(String descricao, Pageable pageable) {

        String desc = descricao != null ? descricao : "";

        return produtoRepository.findAllByNomeLikeIgnoreCaseOrCodigoIgnoreCase(desc, pageable);
    }

    public Produto update(Long id, Produto produto) {

        this.validaProduto(produto);

        return produtoRepository.save(produto);

    }

    public void delete(Long id) {

        Produto produto = readById(id);

        produto.setDataFim(ZonedDateTime.now());

        this.produtoRepository.save(produto);

    }

    public void validaProduto(Produto produto) {

        if (produto.getNome().trim().isEmpty()) {
            throw new RequisicaoComErroException("O NOME do produto é obrigatório.");
        }
        if (produto.getCodigo().trim().isEmpty()) {
            throw new RequisicaoComErroException("O CÓDIGO do produto é obrigatório.");
        }
        if (produto.getTipoProduto().equals(null)) {
            throw new RequisicaoComErroException("O TIPO DE PRODUTO é obrigatório.");
        }
        if (produto.getPreco() < 0.0) {
            throw new RegraNegocioException("O PREÇO do produto não pode ser menor que a zero (0).");
        }

        if (produto.getId() == null) {
            boolean isProdutoFind = produtoRepository.findProdutoByCodigoIgnoreCase(produto.getCodigo()).isPresent();
            if (isProdutoFind) {
                throw new RecursoExistenteException("Já existe um produto cadastrado com o código [ " + produto.getCodigo() + " ] informado.");
            }
        } else {
            Optional<Produto> prodConsultado = this.produtoRepository.findProdutoByCodigoIgnoreCase(produto.getCodigo());

            prodConsultado.ifPresent(produto1 -> {
                if (!produto1.getId().equals(produto.getId())) {
                    throw new RecursoExistenteException("Já existe um produto cadastrado com o código [ " + produto.getCodigo() + " ] informado.");
                }
                System.out.println(prodConsultado);
            });
        }
    }
}
