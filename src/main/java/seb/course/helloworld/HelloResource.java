package seb.course.helloworld;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/hello")
public class HelloResource {
    @Inject
    private GreetingService greetingService;

    @GET // Responds to HTTP GET requests
    public String getHello(@QueryParam("name") String name) {
        // No request.getParameter() needed.
        // JAX-RS extracts "name" from the URL automatically.
        return greetingService.sayHello(name);
    }

}
