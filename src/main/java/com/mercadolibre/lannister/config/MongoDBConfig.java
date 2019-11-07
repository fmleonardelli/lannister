package com.mercadolibre.lannister.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.mercadolibre.lannister.charges.repository.NotificationRepository;
import com.mercadolibre.lannister.config.CustomDateModule;
import com.mongodb.MongoClient;
import io.vavr.jackson.datatype.VavrModule;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MongoDBConfig {

    @Value(value = "${mongo.database}")
    private String databaseName;

    private ObjectMapper mongoObjectMapper() {
        val objectMapper = new ObjectMapper();
        objectMapper.registerModule(new VavrModule(new VavrModule.Settings().deserializeNullAsEmptyCollection(true)));
        objectMapper.registerModule(new CustomDateModule());
        objectMapper.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }

    @Bean
    public NotificationRepository notificationRepository(MongoClient mongoClient) {
        return new NotificationRepository(mongoClient, databaseName, "charges", mongoObjectMapper());
    }
}
