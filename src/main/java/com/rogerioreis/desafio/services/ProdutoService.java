package com.rogerioreis.desafio.services;

import com.rogerioreis.desafio.exception.RecursoExistenteException;
import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import com.rogerioreis.desafio.exception.RegraNegocioException;
import com.rogerioreis.desafio.exception.RequisicaoComErroException;
import com.rogerioreis.desafio.model.Product;
import com.rogerioreis.desafio.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Product create(Product produto) {

        produto.setId(null);

        validaProduto(produto);

        return this.produtoRepository.save(produto);

    }

    public Product readById(Long id) {

        if (id == null) {
            throw new RequisicaoComErroException("Id não informato");
        }

        return produtoRepository.findById(id).orElseThrow(() ->
                new RecursoNaoEncontradoException("Produto com [" + id + "] não encontrado."));

    }

    public Page<Product> page(String descricao, Pageable pageable) {

        String desc = descricao != null ? descricao : "";

        return produtoRepository.findAllByNomeLikeIgnoreCaseOrCodigoIgnoreCase(desc, pageable);
    }

    public Product update(Product produto) {

        this.readById(produto.getId());

        this.validaProduto(produto);

        return produtoRepository.save(produto);

    }

    public void delete(Long id) {

        Product produto = readById(id);

        this.produtoRepository.delete(produto);

    }

    public void validaProduto(Product produto) {

        if (produto.getNome().trim().isEmpty())
            throw new RequisicaoComErroException("O NOME do produto é obrigatório.");

        if (produto.getCodigo().trim().isEmpty())
            throw new RequisicaoComErroException("O CÓDIGO do produto é obrigatório.");

        if (produto.getTipoProduto().equals(null))
            throw new RequisicaoComErroException("O TIPO DE PRODUTO é obrigatório.");

        if (produto.getPreco().compareTo(BigDecimal.ZERO) < 0.0)
            throw new RegraNegocioException("O PREÇO do produto não pode ser menor que a zero (0).");

        if (produto.getId() == null) {
            boolean isProdutoFind = produtoRepository.findProdutoByCodigoIgnoreCase(produto.getCodigo()).isPresent();
            if (isProdutoFind) {
                throw new RecursoExistenteException("Já existe um produto cadastrado com o código [ " + produto.getCodigo() + " ] informado.");
            }
        } else {
            Optional<Product> prodConsultado = this.produtoRepository.findProdutoByCodigoIgnoreCase(produto.getCodigo());

            prodConsultado.ifPresent(produto1 -> {
                if (!produto1.getId().equals(produto.getId())) {
                    throw new RecursoExistenteException("Já existe um produto cadastrado com o código [ " + produto.getCodigo() + " ] informado.");
                }
            });
        }
    }
}
