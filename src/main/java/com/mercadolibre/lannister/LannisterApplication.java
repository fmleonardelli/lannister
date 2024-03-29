package com.mercadolibre.lannister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {MongoDataAutoConfiguration.class, MongoRepositoriesAutoConfiguration.class})
@EnableScheduling
@EnableAsync
public class LannisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(LannisterApplication.class, args);
	}

}
