package com.mercadolibre.lannister.charges.kafka;

import com.mercadolibre.lannister.charges.EventApi;
import com.mercadolibre.lannister.charges.model.ChargeNotification;
import com.mercadolibre.lannister.charges.model.NotificationState;
import com.mercadolibre.lannister.charges.repository.NotificationRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaService {

    @Autowired
    KafkaMessageProducer producer;

    @Autowired
    NotificationRepository repository;

    public void notifyToQueue(EventApi event, ChargeNotification chargeNotification) {
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
