package lk.ijse.apigateway.filter;

import lk.ijse.apigateway.util.JwtValidation;
import lk.ijse.apigateway.util.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouterValidator validator;

    @Autowired
    private JwtValidation jwtValidation;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(SecurityConstants.JWT_HEADER)) {
                    throw new RuntimeException("Missing Authentication Header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(SecurityConstants.JWT_HEADER).get(0);

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    try {
                        authHeader = authHeader.substring("Bearer ".length());
                        jwtValidation.validateJwt(authHeader);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getLocalizedMessage());
                    }
                } else throw new RuntimeException("Invalid Authentication Header");
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}
