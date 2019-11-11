package com.mercadolibre.lannister.charges;

import com.mercadolibre.lannister.charges.kafka.KafkaMessageProducer;
import com.mercadolibre.lannister.charges.kafka.KafkaService;
import com.mercadolibre.lannister.charges.model.ChargeNotification;
import com.mercadolibre.lannister.charges.model.NotificationState;
import com.mercadolibre.lannister.charges.repository.NotificationRepository;
import io.vavr.collection.List;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChargesService {

    @Autowired
    NotificationRepository repository;

    @Autowired
    KafkaService kafkaService;

    Either<Throwable, EventApi> notifyCharge(EventApi event) {
        val notificationCharge = ChargeNotification.map().apply(event);
        val res = notificationCharge.flatMap(n -> repository.save(n));
        if (res.isRight()) {
            kafkaService.notifyToQueue(event, notificationCharge.get());
        }

        return res.map(r -> EventApi.toEventApi().apply(r));
    }

    Either<Throwable, List<EventApi>> findAll() {
        return repository.findAll().map(res -> res.map(c -> EventApi.toEventApi().apply(c)));
    }

}
