package com.mercadolibre.lannister.charges.model;

import com.mercadolibre.lannister.charges.EventApi;
import io.vavr.Function1;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.*;

import java.time.Instant;

import static io.vavr.API.For;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChargeNotification implements ChargeNotificationValidator {
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

     Either<Throwable, ChargeNotification> validate() {
          return checkType(this.type)
                  .flatMap(t -> checkCurrency(this.currency)
                          .flatMap(c -> checkAmount(this.amount)
                                  .map(a -> this)));
     }

     public static Function1<EventApi, Either<Throwable, ChargeNotification>> map() {
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
                  .build().validate();
     }
}
