package seb.course.sessionbeans.async;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Path("/async")
public class AsyncResource {
    @Inject
    private NotificationService notificationService;

    @Inject
    private ReportService reportService;

    @POST
    @Path("/notification")
    public String sendNotification(@QueryParam("m") String message){
        notificationService.send(message);
        return "Message Sent";
    }

    @GET
    @Path("/report")
    public String generateReport() throws ExecutionException, InterruptedException {
        Future<String> reportResultPromise = reportService.generateReport();
        System.out.println("Let's do something else...");
        System.out.println("Now wait for report and return");

        return  reportResultPromise.get();
    }
}
