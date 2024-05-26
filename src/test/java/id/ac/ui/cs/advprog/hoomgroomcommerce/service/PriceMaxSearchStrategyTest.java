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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceMaxSearchStrategyTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private PriceMaxSearchStrategy priceMaxSearchStrategy = new PriceMaxSearchStrategy(productRepository, 110.0);

    private List<Product> mockProducts;

    @BeforeEach
    void setUp() {
        priceMaxSearchStrategy = new PriceMaxSearchStrategy(productRepository, 110.0);
        mockProducts = new ArrayList<>();
    }

    // Helper method to create a product
    private Product createProduct(UUID productId, String productName, String productDescription, String productImage, int productQuantity, Double productPrice, Double productDiscountPrice) {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductImage(productImage);
        product.setProductQuantity((long) productQuantity);
        product.setProductPrice(productPrice);
        product.setProductDiscountPrice(productDiscountPrice);
        HashSet<String> types = new HashSet<>();
        types.add("Furniture");
        types.add("Living Room");
        product.setProductType((Set<String>) types);
        product.setProductState(new AvailableState());
        return product;
    }

    // Helper method to set up the mock repository behavior
    private void setupMockRepository(List<Product> products) {
        when(productRepository.findAll()).thenReturn(products);
    }

    @Test
    void givenProductsWhenSortingByDiscountedPrice() {
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 1", "Description 1", "image1.jpg", 10, 100.0, 10.0));
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 2", "Description 2", "image2.jpg", 10, 150.0, 10.0));
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 3", "Description 3", "image3.jpg", 10, 80.0, 10.0));

        setupMockRepository(mockProducts);

        // Act
        List<Product> sortedProducts = priceMaxSearchStrategy.filterProducts();

        // Assert
        assertEquals(2, sortedProducts.size()); // hanya 2 produk yang memenuhi kriteria (Product 1 dan Product 3)
        assertEquals(90.0, sortedProducts.get(0).getProductPrice() - sortedProducts.get(0).getProductDiscountPrice());
        assertEquals(70.0, sortedProducts.get(1).getProductPrice() - sortedProducts.get(1).getProductDiscountPrice());

        // Memastikan tidak ada produk dalam hasil filter yang memiliki harga setelah diskon lebih besar dari maxPrice
        assertTrue(sortedProducts.stream().allMatch(product -> (product.getProductPrice() - product.getProductDiscountPrice()) <= 150));
    }

    @Test
    void givenProductsWithZeroDiscount() {
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 1", "Description 1", "image1.jpg", 10, 100.0, 0.0));
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 2", "Description 2", "image2.jpg", 10, 150.0, 0.0));
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 3", "Description 3", "image3.jpg", 10, 80.0, 0.0));

        setupMockRepository(mockProducts);

        // Act
        List<Product> sortedProducts = priceMaxSearchStrategy.filterProducts();

        // Assert
        assertEquals(2, sortedProducts.size()); // hanya 2 produk yang memenuhi kriteria (Product 1 dan Product 3)
        assertEquals(100.0, sortedProducts.get(0).getProductPrice() - sortedProducts.get(0).getProductDiscountPrice());
        assertEquals(80.0, sortedProducts.get(1).getProductPrice() - sortedProducts.get(1).getProductDiscountPrice());

        // Memastikan tidak ada produk dalam hasil filter yang memiliki harga setelah diskon lebih besar dari maxPrice
        assertTrue(sortedProducts.stream().allMatch(product -> (product.getProductPrice() - product.getProductDiscountPrice()) <= 110));
    }

}
