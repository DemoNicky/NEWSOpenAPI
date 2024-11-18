package com.dobudobu.gateway_server.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;

@Service
public class JwtUtil {

    public static final String SECRET = "qPfsP8CRv12eHl3zOtTis0xYTVBhjGFG5Av2kX4vbiQ=";

    //digunakan untuk validasi token
    public void validateToken(final String token){
        Jwts.parser().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    //digunakan untuk mengecek apakah token jika di decode sama dengan data di database
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Mengambil klaim (Claims) dari token
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();  // Kembalikan seluruh klaim
    }

}
