package com.mercadolibre.lannister.charges.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.lannister.charges.EventApi;
import io.vavr.Function1;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator), access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class ChargeNotification {

     String type;
     String eventId;
     Double amount;
     String currency;
     Long userId;
     Instant date;
     NotificationState state;
     Option<Instant> processedDate;
     Long version;

     public ChargeNotification withState(NotificationState state) {
          return toBuilder().state(state).build();
     }

     public static Function1<EventApi, ChargeNotification> map() {
          return x -> new ChargeNotification(
                  x.getEventType(),
                  x.getEventId().toString(),
                  x.getAmount(),
                  x.getCurrency(),
                  x.getUserId(),
                  x.getDate(),
                  NotificationState.PENDING,
                  Option.of(Instant.now()),
                  1L);
     }
}
