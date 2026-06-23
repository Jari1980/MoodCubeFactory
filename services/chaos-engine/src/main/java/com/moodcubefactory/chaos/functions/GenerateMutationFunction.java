package com.moodcubefactory.chaos.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

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

        context.getLogger().info("Chaos mutation endpoint called");

        return request.createResponseBuilder(HttpStatus.OK)
                .body("Chaos Engine is alive")
                .build();
    }
}
