package seb.course;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.jms.JMSDestinationDefinition;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.PasswordHash;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

//@JMSDestinationDefinition(
//        name = "java:app/jms/OrderQueue", // The JNDI name we will use to look it up
//        interfaceName = "jakarta.jms.Queue",
//        destinationName = "OrderQueue" // The physical name in the broker
//)
@ApplicationScoped
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:app/jdbc/PostgresDS",
        callerQuery = "SELECT password_hash FROM app_users WHERE username = ?",
        groupsQuery = "SELECT group_name FROM app_user_groups WHERE username = ?",
        hashAlgorithm = PasswordHash.class,
        priority = 10)
@ApplicationPath("/")
@DeclareRoles({"ADMIN", "OWNER", "USER", "GUEST"})
public class RestApplication extends Application {
    // Empty
}
