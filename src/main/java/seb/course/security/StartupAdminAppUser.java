package seb.course.security;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Startup
@Singleton
public class StartupAdminAppUser {
    @PersistenceContext
    private EntityManager em;

    @Inject
    Pbkdf2PasswordHash passwordHasher;

    @PostConstruct
    public void createAdminUser() {
        String adminUsername = "admin";
        String adminPassword = "admin";
        Set<String> adminGroups = Set.of("ADMIN","OWNER");

        AppUser existingAdmin = em.find(AppUser.class, adminUsername);
        if (existingAdmin == null) {

            Map<String, String> parameters = new HashMap<>();
            parameters.put("Pbkdf2PasswordHash.Iterations", "3072");
            parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA256");
            parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
            passwordHasher.initialize(parameters);

            AppUser adminUser = new AppUser();
            adminUser.setUsername(adminUsername);
            String hashedPassword = passwordHasher.generate(adminPassword.toCharArray());
            adminUser.setPassword(hashedPassword);
            adminUser.setGroups(adminGroups);

            em.persist(adminUser);
        }
    }
}
