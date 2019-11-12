package com.mercadolibre.lannister.charges.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CurrencyType {
    ARS("Pesos", "ARS", true),
    Dollar("Dolar", "USD", false);

    String description;
    String identifier;
    Boolean isDefault;
}
