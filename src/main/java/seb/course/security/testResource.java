package seb.course.security;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/auth")
public class testResource {
    @GET
    @Path("/guest")
    @RolesAllowed("GUEST")
    public String guestAccess(){
        return "Hello, This is JUST for Guest!";
    }

    @Path("/user+")
    @GET
    @RolesAllowed(value = {"USER","OWNER", "ADMIN"}) // USER and higher roles
    public String userAccess(){
        return "Hello, This is for USER and higher roles!";
    }

    @Path("/public")
    @GET
    @PermitAll // All if authenticated !
    public String publicAccess() {
        return "Hello, This is for PUBLIC access!";
    }
}
