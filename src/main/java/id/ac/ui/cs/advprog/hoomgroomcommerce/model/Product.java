package id.ac.ui.cs.advprog.hoomgroomcommerce.model;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
    private int productQuantity;
    private Double productPrice;
    private Double productDiscountPrice;

    @Convert(converter = ProductStateConverter.class)
    private ProductState productState;

    @ElementCollection
    private ArrayList<String> productType = new ArrayList<>();

    public boolean checkAvailability() {
        return this.productState.checkAvailability();
    }

    public String printState() {
        return productState.printState();
    }
}

