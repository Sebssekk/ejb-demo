package seb.course.sessionbeans.stateful;

import jakarta.ejb.Remove;
import jakarta.ejb.Stateful;

import java.util.ArrayList;
import java.util.List;

@Stateful
public class ShoppingCart implements ShoppingCartService{
    private final List<String> items = new ArrayList<>();

    public void addItem(String item) {
        items.add(item);
        System.out.println("Added " + item + " - Current size: " + items.size());
    }

    public List<String> getItems() {
        return items;
    }

    // Crucial: You must tell the container when you are done!
    @Remove
    public void checkout() {
        System.out.println("Checking out items: " + items);
        // Process payment...
        // After this method finishes, the bean instance is destroyed.
    }
}
