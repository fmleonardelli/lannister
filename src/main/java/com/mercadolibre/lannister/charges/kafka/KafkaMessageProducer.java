package com.mercadolibre.lannister.charges.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.Instant;

@Component
public class KafkaMessageProducer {

    @Autowired
    private KafkaTemplate<String, EventNotification> kafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;

    public ListenableFuture<SendResult<String, EventNotification>> sendMessage(EventNotification notification) {
        return kafkaTemplate.send(topicName, Instant.now().toString(), notification);
    }
}