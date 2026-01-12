package seb.course.sessionbeans.stateless;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("bad")
public class BadServiceResource {
    @Inject
    private BadService service;

    @GET
    public String getBadService(){
     return service.whatsYourName();
    }

    @PUT
    @Path("/{newName}")
    public String changeBadService(@PathParam("newName") String newName){
        service.changeName(newName);
        return "Name changed : " + newName;
    }
}
