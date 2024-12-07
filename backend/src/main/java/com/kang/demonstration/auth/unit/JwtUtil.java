package com.kang.demonstration.auth.unit;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "c7f1d7e68c56a91e92f3b1c4a3979a93f639946b034ea87b96e5d65eb07888b3";
    private final SecretKey key;

    private final JwtParser parser;

    public JwtUtil() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        this.parser = Jwts.parser()
            .verifyWith(key)
            .build();
    }

    /**
     * 生成 JWT
     *
     * @param claims               自定义声明
     * @param expirationTimeMillis 过期时间（毫秒）
     * @return JWT 字符串
     */
    public String generateToken(Map<String, String> claims, long expirationTimeMillis) {
        Instant now = Instant.now();

        return Jwts.builder()
            .claims().add(claims).and()
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plusMillis(expirationTimeMillis)))
            .signWith(key)
            .compact();
    }

    /**
     * 提取 JWT 中的某个字段
     *
     * @param token          JWT 字符串
     * @param claimsResolver 提取函数
     * @return 提取结果
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = parseToken(token).getBody();
        return claimsResolver.apply(claims);
    }

    /**
     * 从 JWT 中提取用户名
     *
     * @param token JWT 字符串
     * @return 用户名
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 验证 JWT 是否有效
     *
     * @param token    JWT 字符串
     * @param username 用户详情
     * @return 是否有效
     */
    public boolean isTokenValid(String token, String username) {
        String extractedUsername = extractUsername(token);
        return username.equals(extractedUsername) && !isTokenExpired(token);
    }

    /**
     * 检查 JWT 是否过期
     *
     * @param token JWT 字符串
     * @return 是否过期
     */
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    /**
     * 解析 JWT
     *
     * @param token JWT 字符串
     * @return 解析后的 Jws<Claims> 对象
     */
    private Jws<Claims> parseToken(String token) {
        return parser.parseSignedClaims(token);
    }
}