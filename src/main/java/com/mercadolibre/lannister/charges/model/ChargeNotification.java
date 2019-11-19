package com.mercadolibre.lannister.charges.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.mercadolibre.lannister.charges.EventApi;
import io.vavr.Function1;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChargeNotification implements ChargeNotificationValidator {
     String type;
     String eventId;
     @JsonDeserialize(using = NumberDeserializers.BigDecimalDeserializer.class)
     BigDecimal amount;
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
                  .amount(x.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP))
                  .currency(x.getCurrency())
                  .userId(x.getUserId())
                  .date(x.getDate())
                  .state(NotificationState.PENDING)
                  .processedDate(Option.of(Instant.now()))
                  .version(1L)
                  .build().validate();
     }
}
