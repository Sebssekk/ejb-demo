package seb.course.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/rest")
public class HelloRestResource {
    @GET
    @Path("/get-hello")
    public String getHello(){
        return "Hello!";
    }

    @DELETE
    public String delete(){
       return "DELETED!";
    }
    @GET
    @Path("/say/{something}")
    public String sayTo(
            @PathParam("something") String something,
            @QueryParam("to") String name){
        return "I say " +
                something +
                " to " +
                name + ".";
    }

    @Path("/add-post")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserPost addUserPost(UserPost post){
        System.out.println("POST added");
        return post;
    }

    @Path("/add-post-safe")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserPostSafe(UserPost post){
        System.out.println("Checking the post..");
        if (post.isValid()){
            return  Response.status(Response.Status.CREATED)
                    .header("X-Course", "Jakarta EE")
                    .entity(post)
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("X-Course", "Jakarta EE")
                    .entity("Post is not valid!")
                    .build();
        }
    }
}
