package seb.course.persistence.relationships;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import seb.course.exception.StudentNotFoundError;

@Path("/students")
public class StudentResource {
    @Inject
    private StudentService studentService;

    @GET
    @Path("/with-grades/{name}")
    public Student getStudentWithGrades(@PathParam("name") String name) throws StudentNotFoundError {
        return studentService.getStudentByNameWithGrades(name);
    }
    @GET
    @Path("/{id}")
    public Student getStudentById(@PathParam("id") Long id) throws StudentNotFoundError {
        return studentService.getStudentById(id);
    }
}
