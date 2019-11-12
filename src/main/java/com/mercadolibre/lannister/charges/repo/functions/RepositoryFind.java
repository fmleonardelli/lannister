package com.mercadolibre.lannister.charges.repo.functions;

@FunctionalInterface
public interface RepositoryFind<T> {

    Iterable<T> find();
}
