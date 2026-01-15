package seb.course.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable throwable) {
        throwable.printStackTrace();

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // 500
                .entity(new ErrorMessage("ERR-500", "An internal system error occurred. " +
                        "Please contact support."))
                .type("application/json")
                .build();
    }
}
