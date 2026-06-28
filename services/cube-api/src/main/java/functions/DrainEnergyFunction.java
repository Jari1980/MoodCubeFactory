package functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import repository.CubeRepository;
import repository.PostgresCubeRepository;

import java.util.Optional;

public class DrainEnergyFunction {
    @FunctionName("DrainEnergy")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "cubes/drain-energy")
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        context.getLogger().info("Draining energy");

        CubeRepository repository = new PostgresCubeRepository();

        try {
            repository.drainEnergy();

            return request.createResponseBuilder(HttpStatus.OK)
                    .body("Energy drained")
                    .build();

        } catch (Exception e) {
            context.getLogger().severe(e.getMessage());

            return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed drain energy")
                    .build();
        }
    }
}
