package com.mercadolibre.lannister.charges;

import com.mercadolibre.lannister.charges.model.ChargeNotification;
import io.vavr.Function1;
import lombok.*;

import java.time.Instant;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventApi {
     String eventId;
     Double amount;
     String currency;
     String userId;
     String eventType;
     Instant date;

     public static Function1<ChargeNotification, EventApi> toEventApi() {
          return x -> EventApi.builder()
                  .eventId(x.getEventId())
                  .amount(x.getAmount())
                  .currency(x.getCurrency())
                  .userId(x.getUserId())
                  .eventType(x.getType())
                  .date(x.getDate()).build();
     }
}
