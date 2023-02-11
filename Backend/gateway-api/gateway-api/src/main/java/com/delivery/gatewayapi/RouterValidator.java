package com.delivery.gatewayapi;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;


//Pour les routes qui n'ont pas besoin d'être authentifiées
@Component
public class RouterValidator {
    public static final List<String> openApiEndpoints= List.of(
            "/api/v1/auth/validateToken",
            "/api/v1/auth/authenticate",
            "/api/v1/auth/register"
    );
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
