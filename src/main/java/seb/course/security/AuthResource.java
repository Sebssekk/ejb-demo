package seb.course.security;

import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStoreHandler;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthResource {
    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Inject
    private TokenService tokenService;

    @POST
    @Path("/login")
    @Consumes("application/json")
    public Response login(UserCredentials creds) {
        // 1. Ask Jakarta Security: "Check these credentials against Postgres"
        CredentialValidationResult result = identityStoreHandler.validate(
                new UsernamePasswordCredential(creds.username, creds.password)
        );

        System.out.println("Login attempt for user: " + creds.username + " - Status: " + result.getStatus());

        // 2. If Valid, generate JWT
        if (result.getStatus() == CredentialValidationResult.Status.VALID) {
            System.out.println("User " + creds.username + " logged in successfully. ROLES: " + result.getCallerGroups());
            String token = tokenService.generateToken(
                    result.getCallerPrincipal().getName(),
                    result.getCallerGroups()
            );
            return Response.ok(new TokenResponse(token)).build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    // DTOs
    public static class UserCredentials { public String username; public String password; }
    public static class TokenResponse {
        public String token;
        public TokenResponse(String t) { this.token = t; }
    }
}
