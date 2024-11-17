package com.dobudobu.gateway_server.Filter;


import com.dobudobu.gateway_server.Exception.ServiceCustomException.CustomFailedAuthenticationException;
import com.dobudobu.gateway_server.Util.JwtUtil;
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
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) { // Hanya untuk route yang membutuhkan autentikasi
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    // Jika header Authorization tidak ada, lempar exception
                    throw new CustomFailedAuthenticationException("Missing Authorization Header");
                }

                String authHeaders = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeaders != null && authHeaders.startsWith("Bearer ")) {
                    authHeaders = authHeaders.substring(7); // Hapus prefix "Bearer "
                } else {
                    throw new CustomFailedAuthenticationException("Invalid Authorization Header");
                }

                try {
                    jwtUtil.validateToken(authHeaders); // Validasi token JWT
                } catch (Exception e) {
                    System.out.println("Invalid access: " + e.getMessage());
                    throw new CustomFailedAuthenticationException("Unauthorized access to app");
                }
            }
            return chain.filter(exchange); // Lanjutkan ke filter berikutnya jika validasi lolos
        });
    }

    public static class Config {
        // Tambahkan konfigurasi tambahan jika diperlukan
    }

}
