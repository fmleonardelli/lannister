package com.mercadolibre.lannister.charges.repository;

import com.mongodb.client.result.UpdateResult;

@FunctionalInterface
public interface RepositorySave {

    UpdateResult save();
}
