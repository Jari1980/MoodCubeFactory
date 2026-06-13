package com.moodcube.cubeapi;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with HTTP Trigger.
 */
public class GetCubesFunction {
    /**
     * This function listens at endpoint "/api/HttpTriggerJava". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpTriggerJava
     * 2. curl {your host}/api/HttpTriggerJava?name=HTTP%20Query
     */
    @FunctionName("GetCubes")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "cubes"
            ) HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Get cubes triggered");

        List<Map<String, Object>> cubes = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            Map<String, Object> cube = new HashMap<>();
            cube.put("id", i);
            cube.put("color", "blue");
            cube.put("mood", "calm");
            cube.put("energy", 50);
            cubes.add(cube);
        }

        return request.createResponseBuilder(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(cubes)
                .build();
    }
}
