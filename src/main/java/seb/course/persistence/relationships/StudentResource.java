package seb.course.persistence.relationships;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/students")
public class StudentResource {
    @Inject
    private StudentService studentService;

    @GET
    @Path("/with-grades/{name}")
    public Student getStudentWithGrades(@PathParam("name") String name) {
        return studentService.getStudentByNameWithGrades(name);
    }
}
