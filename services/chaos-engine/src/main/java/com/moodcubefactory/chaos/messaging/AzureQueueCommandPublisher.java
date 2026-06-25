package com.moodcubefactory.chaos.messaging;

import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moodcubefactory.chaos.dto.ChaosCommand;

public class AzureQueueCommandPublisher implements ChaosCommandPublisher{
    private final QueueClient queueClient;
    private final ObjectMapper mapper = new ObjectMapper();

    public AzureQueueCommandPublisher() {

        String connectionString =
                System.getenv("AzureWebJobsStorage");

        queueClient =
                new QueueClientBuilder()
                        .connectionString(connectionString)
                        .queueName("chaos-commands")
                        .buildClient();

        queueClient.createIfNotExists();
    }

    @Override
    public void publish(ChaosCommand command) throws Exception {

        String json = mapper.writeValueAsString(command);

        queueClient.sendMessage(json);
    }
}
