package com.dobudobu.gateway_server.Filter;

import com.dobudobu.gateway_server.Exception.ServiceCustomException.CustomFailedAuthenticationException;
import com.dobudobu.gateway_server.Util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new CustomFailedAuthenticationException("Missing Authorization Header");
                }

                String authHeaders = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeaders != null && authHeaders.startsWith("Bearer ")) {
                    authHeaders = authHeaders.substring(7); // Remove "Bearer " prefix
                } else {
                    throw new CustomFailedAuthenticationException("Invalid Authorization Header");
                }

                try {
                    // Validate token and extract userCode
                    Claims claims = jwtUtil.extractAllClaims(authHeaders);
                    String userCode = claims.get("userCode", String.class); // Extract userCode from JWT

                    // Add userCode to the request header to pass it to the service
                    exchange = exchange.mutate()
                            .request(r -> r.header("userCode", userCode))  // Add userCode to header
                            .build();
                } catch (Exception e) {
                    throw new CustomFailedAuthenticationException("Unauthorized access to app");
                }
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {
        // Additional config if needed
    }
}
