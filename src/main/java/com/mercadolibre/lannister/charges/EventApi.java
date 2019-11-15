package com.mercadolibre.lannister.charges;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercadolibre.lannister.charges.model.ChargeNotification;
import io.vavr.Function1;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventApi {
     @NotNull
     String eventId;
     @NotNull
     Double amount;
     @NotNull
     String currency;
     @NotNull
     String userId;
     @NotNull
     String eventType;
     @NotNull
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
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
