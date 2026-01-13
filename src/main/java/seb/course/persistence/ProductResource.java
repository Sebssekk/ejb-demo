package seb.course.persistence;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/products")
public class ProductResource {
    @Inject
    private ProductDAO productDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts(){
        return productDAO.getProducts();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addProduct(Product product){
        productDAO.createProduct(product);
    }
}
