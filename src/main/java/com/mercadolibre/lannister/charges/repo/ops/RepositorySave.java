package com.mercadolibre.lannister.charges.repo.ops;

import com.mongodb.client.result.UpdateResult;

@FunctionalInterface
public interface RepositorySave {

    UpdateResult save();
}
