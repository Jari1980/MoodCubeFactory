package com.moodcubefactory.chaos.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.moodcubefactory.chaos.model.CubeEvent;
import com.moodcubefactory.chaos.service.ChaosService;

public class GenerateMutationFunction {

    @FunctionName("GenerateMutation")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "chaos/mutate")
            HttpRequestMessage<String> request,

            final ExecutionContext context) {

        context.getLogger().info("Generating mutation");

        ChaosService chaosService = new ChaosService();

        CubeEvent event = chaosService.generateMutation();

        return request.createResponseBuilder(HttpStatus.OK)
                .body(event)
                .build();
    }
}
