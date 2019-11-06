package com.mercadolibre.lannister.charges.kafka;

import com.mercadolibre.lannister.charges.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class KafkaMessageProducer {

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;

    public ListenableFuture<SendResult<String, Event>> sendMessage(Event eventApi) {
        return kafkaTemplate.send(topicName, eventApi);
    }
}