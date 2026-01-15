package seb.course.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.enterprise.context.ApplicationScoped;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@ApplicationScoped
public class TokenService {
    private static final String SECRET_KEY = "MySuperSecretKeyForSigningJwtTokens_MustBeLong";

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // 1. Generate Token (Called during Login)
    public String generateToken(String username, Set<String> roles) {
        return Jwts.builder()
                .subject(username) // The "User"
                .claim("groups", roles) // The "Roles"
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // 1 Hour
                .signWith(key)
                .compact();
    }

    // 2. Validate Token (Called by Mechanism)
    public Claims validateToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
