package com.moodcubefactory.chaos.messaging;

import com.moodcubefactory.chaos.dto.ChaosCommand;

public interface ChaosCommandPublisher {
    void publish(ChaosCommand command) throws Exception;
}
