package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.AvailableState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceMinSearchStrategyTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private PriceMinSearchStrategy priceMinSearchStrategy = new PriceMinSearchStrategy(productRepository, 100.0);

    private List<Product> mockProducts;

    @BeforeEach
    void setUp() {
        priceMinSearchStrategy = new PriceMinSearchStrategy(productRepository, 100.0);
        mockProducts = new ArrayList<>();
    }
    private Product createProduct(UUID productId, String productName, String productDescription, String productImage, int productQuantity, Double productPrice, Double productDiscountPrice) {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductImage(productImage);
        product.setProductQuantity(productQuantity);
        product.setProductPrice(productPrice);
        product.setProductDiscountPrice(productDiscountPrice);
        ArrayList<String> types = new ArrayList<>();
        types.add("Furniture");
        types.add("Living Room");
        product.setProductType(types);
        product.setProductState(new AvailableState());
        return product;
    }

    private void setupMockRepository(List<Product> products) {
        when(productRepository.findAll()).thenReturn(products);
    }

    @Test
    void givenProductsWhenSortingByDiscountedPrice() {
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 1", "Description 1", "image1.jpg", 10, 110.0, 10.0));
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 2", "Description 2", "image2.jpg", 10, 150.0, 10.0));
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 3", "Description 3", "image3.jpg", 10, 80.0, 10.0));

        setupMockRepository(mockProducts);

        List<Product> sortedProducts = priceMinSearchStrategy.filterProducts();


        assertEquals(2, sortedProducts.size());
        assertEquals(100.0, sortedProducts.get(0).getProductPrice() - sortedProducts.get(0).getProductDiscountPrice());
        assertEquals(140.0, sortedProducts.get(1).getProductPrice() - sortedProducts.get(1).getProductDiscountPrice());

        assertTrue(sortedProducts.stream().allMatch(product -> (product.getProductPrice() - product.getProductDiscountPrice()) >= 100.0));
    }

    @Test
    void givenProductsWithZeroDiscount() {
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 1", "Description 1", "image1.jpg", 10, 100.0, 0.0));
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 2", "Description 2", "image2.jpg", 10, 150.0, 0.0));
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 3", "Description 3", "image3.jpg", 10, 80.0, 0.0));

        setupMockRepository(mockProducts);

        List<Product> sortedProducts = priceMinSearchStrategy.filterProducts();

        assertEquals(2, sortedProducts.size());
        assertEquals(100.0, sortedProducts.get(0).getProductPrice() - sortedProducts.get(0).getProductDiscountPrice());
        assertEquals(150.0, sortedProducts.get(1).getProductPrice() - sortedProducts.get(1).getProductDiscountPrice());

        assertTrue(sortedProducts.stream().allMatch(product -> (product.getProductPrice() - product.getProductDiscountPrice()) >= 100));
    }

}
