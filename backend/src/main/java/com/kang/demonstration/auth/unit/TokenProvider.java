package com.kang.demonstration.auth.unit;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class TokenProvider {

    @Value(value = "${login-token.expiration.time-millis}")
    private Long expirationTimeMillis = 1000 * 60 * 60 * 24L;

    private static final String USERNAME = "username";
    private static final String AUTHORITIES = "authorities";
    private static final String SECRET_KEY = "c7f1d7e68c56a91e92f3b1c4a3979a93f639946b034ea87b96e5d65eb07888b3";
    private final SecretKey key;

    private final JwtParser parser;

    public TokenProvider() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        this.parser = Jwts.parser()
            .verifyWith(key)
            .build();
    }

    public String generateToken(String username, List<String> authorities) {
        Instant now = Instant.now();
        return Jwts.builder()
            .claims()
            .add(USERNAME, username)
            .add(AUTHORITIES, authorities)
            .and()
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plusMillis(expirationTimeMillis)))
            .signWith(key)
            .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = parseToken(token).getPayload();
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, claims -> claims.get(USERNAME, String.class));
    }

    public boolean isTokenValid(String token, String username) {
        String extractedUsername = extractUsername(token);
        return username.equals(extractedUsername) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private Jws<Claims> parseToken(String token) {
        return parser.parseSignedClaims(token);
    }

    public long getExpirationTimeMillis() {
        return expirationTimeMillis;
    }
}