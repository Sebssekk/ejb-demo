package seb.course.sessionbeans.singleton;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Startup
@Singleton
public class VisitorMetrics {
    private long totalHits = 0;

    @PostConstruct
    public void init() {
        System.out.println(">>> AppMetricsBean started! Ready to count.");
    }

    // WRITE Lock (Default): Block everyone else while I update this value
    @Lock(LockType.WRITE)
    public void incrementHits() {
        totalHits++;
    }

    // READ Lock: Let everyone read at the same time (High Performance)
    @Lock(LockType.READ)
    public long getTotalHits() {
        return totalHits;
    }
}
