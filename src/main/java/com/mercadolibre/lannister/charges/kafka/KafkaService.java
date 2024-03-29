package com.mercadolibre.lannister.charges.kafka;

import com.mercadolibre.lannister.charges.EventApi;
import com.mercadolibre.lannister.charges.model.ChargeNotification;
import com.mercadolibre.lannister.charges.model.NotificationState;
import com.mercadolibre.lannister.charges.repo.NotificationRepository;
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
        ListenableFuture<SendResult<String, EventNotification>> notify = producer.sendMessage(EventNotification.map().apply(event));
        notify.addCallback(new ListenableFutureCallback<SendResult<String, EventNotification>>() {
            @Override
            public void onSuccess(SendResult<String, EventNotification> result) {
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
