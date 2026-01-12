package seb.course.sessionbeans.singleton;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/visitor")
public class VisitorResource {
    @Inject
    private VisitorMetrics metrics;

    @POST // "I am visiting the site"
    public void recordVisit() {
        metrics.incrementHits(); // Queues up if busy
    }

    @GET // "Show me the stats"
    public long getStats() {
        return metrics.getTotalHits(); // Fast, concurrent access
    }
}
