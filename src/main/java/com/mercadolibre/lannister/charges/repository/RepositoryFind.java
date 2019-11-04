package com.mercadolibre.lannister.charges.repository;

@FunctionalInterface
public interface RepositoryFind<T> {

    Iterable<T> find();
}
