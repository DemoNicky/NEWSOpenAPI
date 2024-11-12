package com.dobudobu.gateway_server.Config;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> articleManagementPostServiceRoute(){
        return route("article-management-service")
                .route(RequestPredicates.path("/api/v1/article/create-article"), HandlerFunctions.http("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> articleManagementServiceRoute(){
        return route("article-management-service")
                .route(RequestPredicates.path("/api/v1/article/**"), HandlerFunctions.http("http://localhost:8081"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("articleServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> articleServiceSwaggerRoute(){
        return GatewayRouterFunctions.route("article-management-service")
                .route(RequestPredicates.path("/aggregate/article-management-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8081"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("articleServiceSwaggerCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fileServiceRoute(){
        return route("file-service")
                .route(RequestPredicates.path("/api/v1/file/**"), HandlerFunctions.http("http://localhost:8082"))
//                .filter(CircuitBreakerFilterFunctions.circuitBreaker("fileServiceCircuitBreaker",
//                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fileServiceSwaggerRoute(){
        return GatewayRouterFunctions.route("file-service")
                .route(RequestPredicates.path("/aggregate/file-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8082"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("articleServiceSwaggerCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return RouterFunctions
                .route(RequestPredicates.path("/fallbackRoute"),
                        request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                                .body("Service unavailable, please try again later"));
    }
    
}
