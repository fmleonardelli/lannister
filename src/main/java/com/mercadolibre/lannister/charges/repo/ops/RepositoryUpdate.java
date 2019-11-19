package com.mercadolibre.lannister.charges.repo.ops;

import com.mercadolibre.lannister.charges.model.ChargeNotification;

@FunctionalInterface
public interface RepositoryUpdate {

    ChargeNotification findAndModify();
}
