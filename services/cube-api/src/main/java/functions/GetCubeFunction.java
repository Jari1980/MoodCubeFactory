package functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import model.Cube;
import repository.CubeRepository;
import repository.PostgresCubeRepository;

import java.util.Optional;

public class GetCubeFunction {

    @FunctionName("GetCube")
    public HttpResponseMessage run(

            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS,
                    route = "cubes/{id}"
            )
            HttpRequestMessage<Optional<String>> request,

            @BindingName("id") int id,

            final ExecutionContext context) {

        CubeRepository repository = new PostgresCubeRepository();

        Optional<Cube> cube = repository.getById(id);

        if (cube.isEmpty()) {
            return request
                    .createResponseBuilder(HttpStatus.NOT_FOUND)
                    .body("Cube not found")
                    .build();
        }

        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(cube.get())
                .build();
    }
}
