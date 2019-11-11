package com.mercadolibre.lannister.charges.task;

import io.vavr.collection.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class PlannedTask<T> {

    protected static final Logger logger = LoggerFactory.getLogger(PlannedTask.class);

    public void run() {
        logger.info("Planned task run");
        getDataToProcess().forEach(this::processOneData);
        logger.info("Planned task run end");
    }
    abstract protected List<T> getDataToProcess();
    abstract protected void processOneData(T data);
}
