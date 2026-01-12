package seb.course.sessionbeans.async;

import java.util.concurrent.Future;

public interface ReportService {
    public Future<String> generateReport();
}
