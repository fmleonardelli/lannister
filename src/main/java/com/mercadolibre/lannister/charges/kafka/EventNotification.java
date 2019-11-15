package com.mercadolibre.lannister.charges.kafka;

import com.mercadolibre.lannister.charges.EventApi;
import io.vavr.Function1;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.Instant;

@Value
@AllArgsConstructor
public class EventNotification {
    String eventId;
    Double amount;
    String currency;
    String userId;
    String eventType;
    Instant date;

    public static Function1<EventApi, EventNotification> map() {
        return x -> new EventNotification(
                x.getEventId(),
                x.getAmount(),
                x.getCurrency(),
                x.getUserId(),
                x.getEventType(),
                x.getDate());
    }
}
