package functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import repository.CubeRepository;
import repository.PostgresCubeRepository;

import java.util.Optional;

public class ApplyCommandFunction {
    @FunctionName("ApplyCommand")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "cubes/apply-command")
            HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) throws Exception {

        String body = request.getBody().orElseThrow();

        CubeRepository repository = new PostgresCubeRepository();

        if (body.contains("Something something")) {
            //We add this when we have decided commands
        }

        if (body.contains("Something something")) {
            //We add this when we have decided commands
        }

        if (body.contains("RESET")) {
            repository.resetFactory();
        }

        return request.createResponseBuilder(HttpStatus.OK)
                .body("Command applied")
                .build();
    }
}
