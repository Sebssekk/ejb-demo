package seb.course.sessionbeans.async;

import jakarta.ejb.AsyncResult;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;

import java.util.concurrent.Future;

@Stateless
public class ReportBean implements ReportService{

    @Asynchronous
    public Future<String> generateReport() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new AsyncResult<>("The super heavy report is ready @ /myreport");
    }
}
