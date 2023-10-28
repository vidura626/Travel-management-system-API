package lk.ijse.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static List<String> openRoutes = List.of(
            "/api/user/register",
            "/user/login",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openRoutes.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
