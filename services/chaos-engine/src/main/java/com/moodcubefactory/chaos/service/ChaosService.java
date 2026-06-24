package com.moodcubefactory.chaos.service;

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
}
