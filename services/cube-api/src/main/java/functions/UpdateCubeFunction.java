package functions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import model.Cube;
import repository.CubeRepository;
import repository.PostgresCubeRepository;

import java.util.Optional;

public class UpdateCubeFunction {

    private static final ObjectMapper mapper = new ObjectMapper();

    @FunctionName("UpdateCube")
    public HttpResponseMessage run(

            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.PUT},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "cubes/{id}"
            )
            HttpRequestMessage<Optional<String>> request,

            @BindingName("id") int id,

            final ExecutionContext context)
            throws Exception {

        Cube cube =
                mapper.readValue(
                        request.getBody().orElseThrow(),
                        Cube.class);

        cube.setId(id);

        CubeRepository repository =
                new PostgresCubeRepository();

        Cube updated =
                repository.update(cube);

        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(updated)
                .build();
    }
}
