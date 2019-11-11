package com.mercadolibre.lannister.config;

import com.mercadolibre.lannister.charges.task.NotNotifiedChargesTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class TaskConfig {

    @Autowired
    NotNotifiedChargesTask notNotifiedChargesTask;

    @Scheduled(cron = "${task.schedule.notnotifiedchargestask.cron}")
    public void notNotifiedChargesTaskSchedule() {
        notNotifiedChargesTask.run();
    }
}
