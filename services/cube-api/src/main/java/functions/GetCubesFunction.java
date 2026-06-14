package functions;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import model.Cube;
import repository.CubeRepository;
import repository.PostgresCubeRepository;

/**
 * Azure Functions with HTTP Trigger.
 */
public class GetCubesFunction {


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

        CubeRepository repository = new PostgresCubeRepository();
        List<Cube> cubes = repository.getAll();

        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(cubes)
                .build();
    }
}
