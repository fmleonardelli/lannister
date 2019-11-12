package com.mercadolibre.lannister.charges;

import com.mercadolibre.lannister.charges.api.ChargesParametersApi;
import com.mercadolibre.lannister.charges.api.Paginated;
import com.mercadolibre.lannister.charges.kafka.KafkaService;
import com.mercadolibre.lannister.charges.model.ChargeNotification;
import com.mercadolibre.lannister.charges.repo.ChargesParametersRepository;
import com.mercadolibre.lannister.charges.repo.NotificationRepository;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    Either<Throwable, Paginated<EventApi>> findCharges(ChargesParametersApi parametersApi) {
        val parametersRepository = new ChargesParametersRepository(parametersApi);
        return repository.findByPaginated(parametersRepository).map(r -> new Paginated<>(r.getItems().map(c -> EventApi.toEventApi().apply(c)), r.getOffset(), r.getLimit(), r.getTotal()));
    }

}
