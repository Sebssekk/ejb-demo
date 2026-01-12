package seb.course.sessionbeans.stateless;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/withTax")
public class TaxResource {
    @Inject
    private TaxCalculatorService calculator;

    @GET
    public double getPrice(@QueryParam("raw-price") double rawPrice) {
        return calculator.calculateTotal(rawPrice);
    }
}
