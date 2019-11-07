package com.mercadolibre.lannister.charges.repository;

import com.mercadolibre.lannister.charges.model.ChargeNotification;

@FunctionalInterface
public interface RepositoryUpdate {

    ChargeNotification findAndModify();
}
