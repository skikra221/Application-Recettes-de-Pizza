package com.example.pizzarecipes.dao;

import java.util.List;

/**
 * Interface DAO générique pour les opérations CRUD.
 *
 * @param <T> Type de l'entité gérée
 */
public interface IDao<T> {
    T create(T t);

    T update(T t);

    boolean delete(long id);

    T findById(long id);

    List<T> findAll();
}
