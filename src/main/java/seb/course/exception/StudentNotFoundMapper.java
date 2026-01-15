package seb.course.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class StudentNotFoundMapper implements ExceptionMapper<StudentNotFoundError> {
    @Override
    public Response toResponse(StudentNotFoundError studentNotFoundError) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorMessage("STUDENT_NOT_FOUND", studentNotFoundError.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
