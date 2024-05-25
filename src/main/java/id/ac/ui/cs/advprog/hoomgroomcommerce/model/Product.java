package id.ac.ui.cs.advprog.hoomgroomcommerce.model;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

@Entity
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID productId;
    private String productName;
    private String productDescription;
    private String productImage;
    private Long productQuantity;
    private Double productPrice;
    private Double productDiscountPrice;

    @Convert(converter = ProductStateConverter.class)
    private ProductState productState;

    @ElementCollection
    private Set<String> productType = new HashSet<>();

    // Default constructor
    public Product() {
        this.productState = new AvailableState(); // Initialize productState as AvailableState
    }

    public boolean checkAvailability() {
        return this.productState.checkAvailability();
    }

    public String printState() {
        return productState.printState();
    }
}
