package com.mercadolibre.lannister.charges;

import com.mercadolibre.lannister.charges.kafka.KafkaMessageProducer;
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
    KafkaMessageProducer producer;

    @Autowired
    NotificationRepository repository;

    Either<Throwable, ChargeNotification> notifyCharge(EventApi event) {
        val notificationCharge = ChargeNotification.map().apply(event);
        val res = repository.save(notificationCharge).map(r -> r);
        if (res.isRight()) {
            notifyToQueue(event, notificationCharge);
        }

        return res;
    }

    Either<Throwable, List<ChargeNotification>> findAll() {
       return repository.findAll();
    }

    private void notifyToQueue(EventApi event, ChargeNotification chargeNotification) {
        ListenableFuture<SendResult<String, EventApi>> notify = producer.sendMessage(event);
        notify.addCallback(new ListenableFutureCallback<SendResult<String, EventApi>>() {
            @Override
            public void onSuccess(SendResult<String, EventApi> result) {
                updateNotifyState(chargeNotification.withState(NotificationState.PROCESSED));
            }

            @Override
            public void onFailure(Throwable ex) {
                updateNotifyState(chargeNotification.withState(NotificationState.NOT_PROCESSED));
            }
        });
    }

    private void updateNotifyState(ChargeNotification notification) {
        val res = repository.update(notification);
    }

}
