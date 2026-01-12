package seb.course.sessionbeans.async;

import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;

import java.util.concurrent.TimeUnit;

@Stateless
public class MockSendEmail implements NotificationService{

    @Asynchronous
    public void send(String message) {
        System.out.println("[*] Start sending the message...");
        long startTime = System.currentTimeMillis();
        for (int i=0; i< 5; i++){
            System.out.println("...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("[*] Message successfully sent.");
        System.out.println(">>> Elapsed Time: " + elapsedTime +"ms");
    }
}
