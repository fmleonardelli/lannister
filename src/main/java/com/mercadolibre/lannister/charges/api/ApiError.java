package com.mercadolibre.lannister.charges.api;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ApiError {
    String code;
    String message;
}
