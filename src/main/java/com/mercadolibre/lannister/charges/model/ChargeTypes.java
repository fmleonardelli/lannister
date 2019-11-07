package com.mercadolibre.lannister.charges.model;

import io.vavr.collection.List;

public enum ChargeTypes {

    MARKETPLACE("MARKETPLACE", List.of("CLASIFICADO", "VENTA", "ENVÍO")),
    SERVICES("CREDITO", List.of("FIDELIDAD", "PUBLICIDAD")),
    EXTERN("EXTERNO", List.of("MERCADOPAGO", "MERCADOSHOP"));

    private String category;
    private List<String> types;

    private ChargeTypes(String category, List<String> types) {
        this.category = category;
        this.types = types;
    }
}
