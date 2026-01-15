package seb.course.security;

import io.jsonwebtoken.Claims;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class JwtAuthenticationMechanism implements HttpAuthenticationMechanism {
    @Inject
    private TokenService tokenService;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest req, HttpServletResponse res, HttpMessageContext context) {
        String authHeader = req.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                // 2. Verify Signature & Expiration
                System.out.println("Validating token: " + token);
                Claims claims = tokenService.validateToken(token);

                String username = claims.getSubject();

                // Extract roles from the List inside the claim
                List<String> groupList = claims.get("groups", List.class);
                Set<String> roles = new HashSet<>(groupList);

                // 3. Notify Container: "This request is from [username] with [roles]"
                return context.notifyContainerAboutLogin(username, roles);

            } catch (Exception e) {
                // Token is expired or tampered
                System.out.println("Token validation failed: " + e.getMessage());
                return context.responseUnauthorized();
            }
        }

        // 4. No Token? Treat as "Guest".
        // If the endpoint requires login, the Container will assume the user is null and block it.
        return context.doNothing();
    }
}
