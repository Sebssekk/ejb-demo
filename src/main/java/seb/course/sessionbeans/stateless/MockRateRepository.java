package seb.course.sessionbeans.stateless;

import jakarta.ejb.Stateless;

@Stateless
public class MockRateRepository implements RateRepoService{
    public double getCurrentRate(){
        // Simulating DB Read
        System.out.println(">>> RateRepository: Fetching rate from Database...");
        return 0.20;
    }
}
