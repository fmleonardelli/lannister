package com.mercadolibre.lannister.charges.repo.functions;

import com.mercadolibre.lannister.charges.model.ChargeNotification;

@FunctionalInterface
public interface RepositoryUpdate {

    ChargeNotification findAndModify();
}
