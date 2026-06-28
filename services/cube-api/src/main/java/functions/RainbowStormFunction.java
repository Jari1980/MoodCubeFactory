package functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import repository.CubeRepository;
import repository.PostgresCubeRepository;

import java.util.Optional;

public class RainbowStormFunction {
    @FunctionName("RainbowStorm")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "cubes/rainbow-storm")
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        context.getLogger().info("Rainbow storm triggered");

        CubeRepository repository = new PostgresCubeRepository();

        try {
            repository.rainbowStorm();

            return request.createResponseBuilder(HttpStatus.OK)
                    .body("Rainbow storm applied")
                    .build();

        } catch (Exception e) {
            context.getLogger().severe(e.getMessage());

            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed rainbow storm")
                    .build();
        }
    }
}
