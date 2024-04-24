package id.ac.ui.cs.advprog.hoomgroomcommerce.model;


import java.util.UUID;


import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Product {
    private UUID productId;
    private String productName;
    private String productDescription;
    private String productImage;
    private int productQuantity;
    private Double productPrice;
    private Double productDiscountPrice;
    private ProductState productState;

    public boolean checkAvailability() {
        return this.productState.checkAvailability();
    }

    public String printState() {
        return productState.printState();
    }
}

