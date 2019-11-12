package com.mercadolibre.lannister.charges.api;

import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.Instant;

@Value
@AllArgsConstructor
public class ChargesParametersApi {
    Option<String> eventType;
    Option<String> eventId;
    Option<String> userId;
    Option<Integer> limit;
    Option<Integer> offset;
}
