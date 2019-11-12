package com.mercadolibre.lannister.charges.api;

public class ValidationError extends Exception {

    public ValidationError(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationError(String message) {
        super(message);
    }
}
