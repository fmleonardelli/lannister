package com.mercadolibre.lannister.charges.repo.interfaces;

@FunctionalInterface
public interface RepositoryFind<T> {

    Iterable<T> find();
}
