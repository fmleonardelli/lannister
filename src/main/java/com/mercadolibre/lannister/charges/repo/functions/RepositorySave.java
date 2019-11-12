package com.mercadolibre.lannister.charges.repo.functions;

import com.mongodb.client.result.UpdateResult;

@FunctionalInterface
public interface RepositorySave {

    UpdateResult save();
}
