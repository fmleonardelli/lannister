package com.mercadolibre.lannister.charges.model;

import io.vavr.collection.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ChargeTypes {

    MARKETPLACE("MARKETPLACE", List.of("CLASIFICADO", "VENTA", "ENVÍO")),
    SERVICES("CREDITO", List.of("FIDELIDAD", "PUBLICIDAD")),
    EXTERNAL("EXTERNO", List.of("MERCADOPAGO", "MERCADOSHOP"));

    String category;
    List<String> types;
}
