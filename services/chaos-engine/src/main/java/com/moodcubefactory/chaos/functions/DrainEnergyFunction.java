package com.moodcubefactory.chaos.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.moodcubefactory.chaos.dto.ChaosCommand;
import com.moodcubefactory.chaos.messaging.AzureQueueCommandPublisher;
import com.moodcubefactory.chaos.messaging.ChaosCommandPublisher;

public class DrainEnergyFunction {
    @FunctionName("DrainEnergy")
    public HttpResponseMessage run(

            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "chaos/drain-energy")
            HttpRequestMessage<String> request,

            final ExecutionContext context) {

        try {

            ChaosCommand command = new ChaosCommand("DRAIN_ENERGY");

            ChaosCommandPublisher publisher = new AzureQueueCommandPublisher();

            publisher.publish(command);

            return request
                    .createResponseBuilder(HttpStatus.ACCEPTED)
                    .body(command)
                    .build();

        } catch (Exception ex) {

            context.getLogger().severe(ex.getMessage());

            return request
                    .createResponseBuilder(
                            HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to publish command")
                    .build();
        }
    }
}
