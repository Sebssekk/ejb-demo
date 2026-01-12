package seb.course.sessionbeans.stateful;

import java.util.List;

public interface ShoppingCartService {
    public List<String> getItems();
    public void addItem(String item);
    public void checkout();
}
