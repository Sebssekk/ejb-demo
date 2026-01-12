package seb.course.sessionbeans.stateless;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class TaxCalculator implements TaxCalculatorService{
    @Inject
    private RateRepoService rateRepo;

    public double calculateTotal(double price){
        double taxRate = rateRepo.getCurrentRate(); // e.g., 0.20
        return price + (price * taxRate);
    }
}
