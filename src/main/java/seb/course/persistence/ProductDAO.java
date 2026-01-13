package seb.course.persistence;

import java.util.List;

public interface ProductDAO {
    public List<Product> getProducts();
    public Product getProductById(Long id);
    public Product getProductByName(String name);
    public void createProduct(Product product);
    public Product updateProduct(Long id, Product product);
    public void deleteProduct(Long id);
}
