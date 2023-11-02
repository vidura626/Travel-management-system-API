package lk.ijse.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public static List<String> openRoutes = List.of(
            "/api/user/register",
            "/user/login",
            "/eureka",
            "/api/hotel/all",
            "/api/hotel/{hotelId}",
            "/api/vehicle/all"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openRoutes.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
