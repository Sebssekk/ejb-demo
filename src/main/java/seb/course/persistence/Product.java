package seb.course.persistence;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
@NamedQuery(
        name = "Product.findByName",
        query = "SELECT p FROM Product p WHERE p.name = :name"
)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-Increment
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private double price;

    public Product() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
