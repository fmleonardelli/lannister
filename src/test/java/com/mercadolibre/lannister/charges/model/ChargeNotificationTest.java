package com.mercadolibre.lannister.charges.model;

import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

public class ChargeNotificationTest {

    @Test
    public void checkChargeNotificationProperties() {
        ChargeNotification notification = ChargeNotification
                .builder()
                .type("type")
                .eventId("eventId")
                .amount(Double.MIN_VALUE)
                .currency(CurrencyType.ARS.identifier)
                .userId("userId")
                .date(Instant.now())
                .state(NotificationState.PROCESSED)
                .processedDate(Option.none())
                .version(1L).build();

        assertThat(notification, hasProperty("type"));
        assertThat(notification, hasProperty("eventId"));
        assertThat(notification, hasProperty("amount"));
        assertThat(notification, hasProperty("currency"));
        assertThat(notification, hasProperty("userId"));
        assertThat(notification, hasProperty("date"));
        assertThat(notification, hasProperty("state"));
        assertThat(notification, hasProperty("processedDate"));
        assertThat(notification, hasProperty("version"));
    }

    @Test
    public void checkChargeNotificationTypeNotValid() {
        ChargeNotification notification = ChargeNotification
                .builder()
                .type("WITHOUT TYPE")
                .eventId("eventId")
                .amount(Double.MIN_VALUE)
                .currency(CurrencyType.ARS.identifier)
                .userId("userId")
                .date(Instant.now())
                .state(NotificationState.PROCESSED)
                .processedDate(Option.none())
                .version(1L).build();
        assert notification.validate().isLeft();
    }

    @Test
    public void checkChargeNotificationTypeValid() {
        ChargeNotification notification = ChargeNotification
                .builder()
                .type("CLASIFICADO")
                .eventId("eventId")
                .amount(Double.MIN_VALUE)
                .currency(CurrencyType.ARS.identifier)
                .userId("userId")
                .date(Instant.now())
                .state(NotificationState.PROCESSED)
                .processedDate(Option.none())
                .version(1L).build();
        assert notification.validate().isRight();
    }

    @Test
    public void checkChargeNotificationCurrencyNotValid() {
        ChargeNotification notification = ChargeNotification
                .builder()
                .type("CLASIFICADO")
                .eventId("eventId")
                .amount(Double.MIN_VALUE)
                .currency("currency")
                .userId("userId")
                .date(Instant.now())
                .state(NotificationState.PROCESSED)
                .processedDate(Option.none())
                .version(1L).build();
        assert notification.validate().isLeft();
    }

    @Test
    public void checkChargeNotificationCurrencyValid() {
        ChargeNotification notification = ChargeNotification
                .builder()
                .type("CLASIFICADO")
                .eventId("eventId")
                .amount(Double.MIN_VALUE)
                .currency(CurrencyType.ARS.identifier)
                .userId("userId")
                .date(Instant.now())
                .state(NotificationState.PROCESSED)
                .processedDate(Option.none())
                .version(1L).build();
        assert notification.validate().isRight();
    }

    @Test
    public void checkChargeNotificationAmountNotValid() {
        ChargeNotification notification = ChargeNotification
                .builder()
                .type("CLASIFICADO")
                .eventId("eventId")
                .amount(-1d)
                .currency(CurrencyType.ARS.identifier)
                .userId("userId")
                .date(Instant.now())
                .state(NotificationState.PROCESSED)
                .processedDate(Option.none())
                .version(1L).build();
        assert notification.validate().isLeft();
    }

    @Test
    public void checkChargeNotificationAmountValid() {
        ChargeNotification notification = ChargeNotification
                .builder()
                .type("CLASIFICADO")
                .eventId("eventId")
                .amount(500d)
                .currency(CurrencyType.ARS.identifier)
                .userId("userId")
                .date(Instant.now())
                .state(NotificationState.PROCESSED)
                .processedDate(Option.none())
                .version(1L).build();
        assert notification.validate().isRight();
    }
}
