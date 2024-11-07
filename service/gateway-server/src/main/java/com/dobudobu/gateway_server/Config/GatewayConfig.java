package com.dobudobu.gateway_server.Config;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class GatewayConfig {
    
    @Bean
    public RouterFunction<ServerResponse> fileServiceRoute(){
        return route("file-service")
                .route(RequestPredicates.path("/api/v1/file/**"), HandlerFunctions.http("http://file-service:8082"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> articleManagementServiceRoute(){
        return route("article-management-service")
                .route(RequestPredicates.path("/api/v1/article/**"), HandlerFunctions.http("http://article-management-service:8081"))
                .build();
    }
    
}
