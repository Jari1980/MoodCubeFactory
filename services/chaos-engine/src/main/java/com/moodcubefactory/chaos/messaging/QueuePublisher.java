package com.moodcubefactory.chaos.messaging;

import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moodcubefactory.chaos.model.CubeEvent;

public class QueuePublisher {
    private final QueueClient queueClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public QueuePublisher() {

        queueClient = new QueueClientBuilder()
                        .connectionString(System.getenv("AzureWebJobsStorage"))
                        .queueName("cube-events")
                        .buildClient();

        queueClient.createIfNotExists();
    }

    public void publish(CubeEvent event) {

        try {
            String json = mapper.writeValueAsString(event);
            queueClient.sendMessage(json);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
