package seb.course.sessionbeans.stateful;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Path("/cart")
public class ShoppingCartResource implements Serializable {
    @Inject
    private ShoppingCartService shoppingCart;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getCartContent(){
        return shoppingCart.getItems();
    }

    @POST
    @Path("/add")
    public String addItem(@QueryParam("product") String item){
        shoppingCart.addItem(item);
        return item + " added to cart!";
    }

    @POST
    @Path("/checkout")
    public String checkout(){
        shoppingCart.checkout();
        return "Checkout! Thanks for your purchase";
    }
}
