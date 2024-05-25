package id.ac.ui.cs.advprog.hoomgroomcommerce.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;


public class ProductTest {
    Product product;


    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId(UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"));
        this.product.setProductName("Sofa Fabric");
        this.product.setProductDescription("This is a comfortable sofa made of high-quality fabric.");
        this.product.setProductImage("https://example.com/sofa_fabric_image.jpg");
        this.product.setProductQuantity(50L);
        this.product.setProductPrice(50000.0);
        this.product.setProductDiscountPrice(40000.0);
        HashSet<String> types = new HashSet<>();
        types.add("Furniture");
        types.add("Living Room");
        product.setProductType(types);
        product.setProductState(new AvailableState()); // set a ProductState
    }



    @Test
    void testGetProductId() {
        assertEquals(UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"), this.product.getProductId());
    }


    @Test
    void testGetProductName() {
        assertEquals("Sofa Fabric", this.product.getProductName());
    }


    @Test
    void testGetProductDescription() {
        assertEquals("This is a comfortable sofa made of high-quality fabric.", this.product.getProductDescription());
    }


    @Test
    void testGetProductImage() {
        assertEquals("https://example.com/sofa_fabric_image.jpg", this.product.getProductImage());
    }


    @Test
    void testGetProductQuantity() {
        assertEquals(50, this.product.getProductQuantity());
    }


    @Test
    void testGetProductPrice() {
        assertEquals(50000.0, this.product.getProductPrice());
    }


    @Test
    void testGetProductDiscountPrice() {
        assertEquals(40000.0, this.product.getProductDiscountPrice());
    }

    @Test
    void testGetProductType() {
        ArrayList<String> typesTest = new ArrayList<>();
        typesTest.add("Furniture");
        typesTest.add("Living Room");
        assertEquals(typesTest, this.product.getProductType());
    }

    @Test
    void testGetProductState() {
        assertInstanceOf(AvailableState.class, this.product.getProductState());
    }
}