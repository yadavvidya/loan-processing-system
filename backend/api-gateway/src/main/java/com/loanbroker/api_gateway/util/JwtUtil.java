package com.loanbroker.api_gateway.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration-ms:3600000}") // default 1 hour
    private long expirationMs;

    private SecretKey signingKey;

    @PostConstruct
    public void init() {
        // JJWT requires at least 256-bit key for HS256
        signingKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Generate JWT token
     */
    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validate and parse token
     */
    public Jws<Claims> validate(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException ex) {
            throw new RuntimeException("Invalid JWT token: " + ex.getMessage(), ex);
        }
    }

    /**
     * Extract username/userId from token
     */
    public String getSubject(String token) {
        return validate(token).getBody().getSubject();
    }
}

