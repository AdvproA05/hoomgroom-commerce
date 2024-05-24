package id.ac.ui.cs.advprog.hoomgroomcommerce.model;


import java.util.ArrayList;
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
    private ArrayList<String> productType = new ArrayList<>();
}

