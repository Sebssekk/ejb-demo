package seb.course.helloworld;

import jakarta.ejb.Stateless;

@Stateless
public class PoliteGreetingService implements GreetingService{
    // NO CONSTRUCTOR! - EJB will manage it

    public String sayHello(String name) {
        return "Hello, " + name + ", " +
                "Nice to meet you here! " +
                "\n>>> (Processed by EJB) <<<";
    }
}
