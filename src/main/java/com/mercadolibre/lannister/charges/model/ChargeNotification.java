package com.mercadolibre.lannister.charges.model;

import com.mercadolibre.lannister.charges.EventApi;
import io.vavr.Function1;
import io.vavr.control.Option;
import lombok.*;

import java.time.Instant;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChargeNotification {

     String type;
     String eventId;
     Double amount;
     String currency;
     String userId;
     Instant date;
     NotificationState state;
     Option<Instant> processedDate;
     Long version;

     public ChargeNotification withState(NotificationState state) {
          return toBuilder().state(state).build();
     }

     public static Function1<EventApi, ChargeNotification> map() {
          return x -> ChargeNotification.builder()
                  .type(x.getEventType())
                  .eventId(x.getEventId())
                  .amount(x.getAmount())
                  .currency(x.getCurrency())
                  .userId(x.getUserId())
                  .date(x.getDate())
                  .state(NotificationState.PENDING)
                  .processedDate(Option.of(Instant.now()))
                  .version(1L)
                  .build();
     }
}
