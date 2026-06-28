package functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import repository.CubeRepository;
import repository.PostgresCubeRepository;

import java.util.Optional;

public class ResetFactoryFunction {
    @FunctionName("ResetFactory")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "cubes/reset-factory")
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        context.getLogger().info("Reset factory triggered");

        CubeRepository repository = new PostgresCubeRepository();

        try {
            repository.resetFactory();

            return request.createResponseBuilder(HttpStatus.OK)
                    .body("Factory reset successfully")
                    .build();

        } catch (Exception e) {

            context.getLogger().severe("Reset failed: " + e.getMessage());

            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Reset failed")
                    .build();
        }
    }
}
