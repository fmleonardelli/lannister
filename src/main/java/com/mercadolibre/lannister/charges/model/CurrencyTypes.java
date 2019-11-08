package com.mercadolibre.lannister.charges.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CurrencyTypes {
    ARS("Pesos", "ARS"),
    Dollar("Dolar", "USD");

    String description;
    String identifier;
}
