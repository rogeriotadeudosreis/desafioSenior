package com.rogerioreis.desafio.interfaces;

import com.rogerioreis.desafio.exception.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenericService<T, ID> {

    @Autowired
    JpaRepository<T, ID> getRepository();

    default T create(T anyEntity) {
        anyEntity = getRepository().save(anyEntity);
        return anyEntity;
    }

    default T readById(ID id) {
        return getRepository().findById(id).orElseThrow(
                () -> new RecursoNaoEncontradoException("[" + id + "] não encontrado."));
    }

    default List<T> read() {
        List<T> list = getRepository().findAll();
        return list;
    }

    Page<T> read(String descricao, Pageable pageable);

    default T update(T anyEntity, ID id) {
        this.readById(id);
        return getRepository().save(anyEntity);
    }

    default void delete(ID id) {
        T anyEntity = readById(id);
        this.getRepository().delete(anyEntity);
    }

}












