package com.dobudobu.gateway_server.Config;


import com.dobudobu.gateway_server.Filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // User Service dengan Authentication Filter
                .route("user-service", r -> r.path("/api/v1/user/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8083"))

                // Role Service dengan Authentication Filter
                .route("role-service", r -> r.path("/api/v1/role/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8083"))

                // Article Service dengan Authentication Filter
                .route("article-service", r -> r.path("/api/v1/article/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8081"))

                // Swagger Documentation - Tanpa Filter
                .route("swagger-user-service", r -> r.path("/aggregate/user-service/v3/api-docs")
                        .uri("http://localhost:8083"))

                .route("swagger-article-service", r -> r.path("/aggregate/article-management-service/v3/api-docs")
                        .uri("http://localhost:8081"))

                .route("swagger-file-service", r -> r.path("/aggregate/file-service/v3/api-docs")
                        .uri("http://localhost:8082"))

                // File Service dengan Authentication Filter
                .route("file-service", r -> r.path("/api/v1/file/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("http://localhost:8082"))

                .build();
    }

    // Fallback Route untuk Circuit Breaker
    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return RouterFunctions.route(RequestPredicates.path("/fallbackRoute"),
                request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service unavailable, please try again later"));
    }
}
