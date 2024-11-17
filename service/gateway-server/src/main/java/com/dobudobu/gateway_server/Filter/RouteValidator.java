package com.dobudobu.gateway_server.Filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    // Daftar endpoint yang tidak memerlukan autentikasi
    public static final List<String> openApiEndpoints = List.of(
            "/api/v1/user/register",
            "/api/v1/user/token",
            "/eureka"
    );

    // Predicate untuk memvalidasi apakah permintaan perlu autentikasi
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));

}
