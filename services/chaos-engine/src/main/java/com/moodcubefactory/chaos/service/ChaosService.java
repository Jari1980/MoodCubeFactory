package com.moodcubefactory.chaos.service;

import com.moodcubefactory.chaos.dto.ChaosCommand;
import com.moodcubefactory.chaos.messaging.AzureQueueCommandPublisher;
import com.moodcubefactory.chaos.messaging.QueuePublisher;
import com.moodcubefactory.chaos.model.CubeEvent;

public class ChaosService {
    private final RandomMutationGenerator generator;
    private final QueuePublisher publisher;

    public ChaosService() {
        this.generator = new RandomMutationGenerator();
        this.publisher = new QueuePublisher();
    }

    public CubeEvent generateMutation() {

        CubeEvent event = generator.generate();
        publisher.publish(event);

        return event;
    }

    public CubeEvent resetFactory() {

        CubeEvent event = new ResetFactoryGenerator().generate();

        ChaosCommand command = new ChaosCommand("RESET_FACTORY");

        try {
            new AzureQueueCommandPublisher().publish(command);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return event;
    }
}
