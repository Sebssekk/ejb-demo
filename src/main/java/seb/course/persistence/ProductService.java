package seb.course.persistence;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ProductService implements ProductDAO{

    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;

    @Override
    public List<Product> getProducts() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
    @Override
    public Product getProductById(Long id) {
        return em.find(Product.class, id);
    }

    @Override
    public Product getProductByName(String name) {
        return em.createNamedQuery("Product.findByName", Product.class)
                .setParameter("name", name)
                .getSingleResult();
    }
    @Override
    public void createProduct(Product product) {
        em.persist(product);
    }
    @Override
    public Product updateProduct(Long id, Product newProduct) {
        Product existingProduct = em.find(Product.class, id);
        if (existingProduct != null) {
            existingProduct.setName(newProduct.getName());
            existingProduct.setPrice(newProduct.getPrice());
            em.merge(existingProduct);
        }
        return existingProduct;
    }
    @Override
    public void deleteProduct(Long id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }
}
