package com.mercadolibre.lannister.charges.api;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.Instant;

@Value
@AllArgsConstructor
public class ChargesParametersApi implements ParametersApi {
    Option<String> eventType;
    Option<String> eventId;
    Option<String> userId;
    Option<String> dateFrom;
    Option<String> dateTo;
    Option<Integer> limit;
    Option<Integer> offset;

    public Option<Instant> fromDate() {
        return dateFrom.map(f -> from(f).getOrElse(Instant.now()));
    }

    public Option<Instant> toDate() {
        return dateTo.map(t -> to(t).getOrElse(Instant.now()));
    }

}
