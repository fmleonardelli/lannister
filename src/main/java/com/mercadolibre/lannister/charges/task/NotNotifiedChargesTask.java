package com.mercadolibre.lannister.charges.task;

import com.mercadolibre.lannister.charges.ChargesService;
import com.mercadolibre.lannister.charges.EventApi;
import com.mercadolibre.lannister.charges.kafka.KafkaService;
import com.mercadolibre.lannister.charges.model.ChargeNotification;
import com.mercadolibre.lannister.charges.repository.NotificationRepository;
import io.vavr.collection.List;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotNotifiedChargesTask extends PlannedTask<ChargeNotification> {

    @Autowired
    NotificationRepository repository;

    @Autowired
    ChargesService chargesService;

    @Autowired
    KafkaService kafkaService;

    protected List<ChargeNotification> getDataToProcess() {
        val dataToProcess = repository.findAll();
        if (dataToProcess.isLeft()) {
            logger.error("Get Data to Process", dataToProcess.getLeft());
        }
        return dataToProcess.getOrElse(List.empty());
    }

    protected void processOneData(ChargeNotification data) {
        logger.info("Process one data: " + data.toString());
        kafkaService.notifyToQueue(EventApi.toEventApi().apply(data), data);
        logger.info("Process one data end");
    }
}

