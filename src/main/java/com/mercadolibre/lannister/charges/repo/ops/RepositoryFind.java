package com.mercadolibre.lannister.charges.repo.ops;

@FunctionalInterface
public interface RepositoryFind<T> {

    Iterable<T> find();
}
