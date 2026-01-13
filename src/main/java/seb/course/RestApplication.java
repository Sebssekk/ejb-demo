package seb.course;

import jakarta.jms.JMSDestinationDefinition;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

//@JMSDestinationDefinition(
//        name = "java:app/jms/OrderQueue", // The JNDI name we will use to look it up
//        interfaceName = "jakarta.jms.Queue",
//        destinationName = "OrderQueue" // The physical name in the broker
//)
@ApplicationPath("/")
public class RestApplication extends Application {
    // Empty
}
