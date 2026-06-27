package com.moodcubefactory.chaos.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.moodcubefactory.chaos.model.CubeEvent;
import com.moodcubefactory.chaos.service.ChaosService;

public class ResetFactoryFunction {
    @FunctionName("ResetFactory")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "chaos/reset-factory")
            HttpRequestMessage<String> request,

            final ExecutionContext context) {

        context.getLogger().info("Resetting factory");

        ChaosService chaosService = new ChaosService();

        CubeEvent event = chaosService.resetFactory();

        return request.createResponseBuilder(HttpStatus.OK)
                .body(event)
                .build();
    }
}
