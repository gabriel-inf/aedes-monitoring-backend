package com.stepien.aedes.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.stepien.aedes.model.Chunks;
import com.stepien.aedes.service.LocalizationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherSyncJob {
    
    Logger logger = LoggerFactory.getLogger(WeatherSyncJob.class);

    private static final int BATCH_SIZE = 100;

    @Autowired
    private LocalizationService localizationService;

    @Autowired
    WeatherInformationConsumer weatherInformationConsumer;

    public void processWeatherInformationForAllChunks() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Chunks> chunks = localizationService.getAllChunks();

        int start = 0;
        for (int batchNumber = 0; batchNumber < Math.ceil(chunks.size()/BATCH_SIZE); batchNumber++) {
            int end = BATCH_SIZE + BATCH_SIZE*batchNumber;
            logger.info(String.format("Dispatched batch #%d [%d - %d]", batchNumber, start, end));

            executorService.execute(new WeatherWorker(chunks.subList(start, Math.min(end, chunks.size())), weatherInformationConsumer));
            start = end;
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(10, TimeUnit.MINUTES);
        } catch(InterruptedException e) {
            logger.error("processWeatherInformationForAllChunks - failed to await threads", e);
        }

    }

}
