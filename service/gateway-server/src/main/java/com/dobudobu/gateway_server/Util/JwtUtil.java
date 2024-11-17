package com.dobudobu.gateway_server.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

}
