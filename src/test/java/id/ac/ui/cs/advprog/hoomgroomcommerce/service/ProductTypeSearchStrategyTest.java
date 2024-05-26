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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductTypeSearchStrategyTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductTypeSearchStrategy productTypeSearchStrategy;

    private List<Product> mockProducts;

    @BeforeEach
    void setUp() {
        productTypeSearchStrategy = new ProductTypeSearchStrategy(productRepository, new ArrayList<>(Arrays.asList("Furniture", "Living Room"))
        );
        mockProducts = new ArrayList<>();
    }


    Product createProduct(UUID productId, String productName, String productDescription, String productImage, int productQuantity, Double productPrice, Double productDiscountPrice , HashSet<String> productType){
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductImage(productImage);
        product.setProductQuantity((long) productQuantity);
        product.setProductPrice(productPrice);
        product.setProductDiscountPrice(productDiscountPrice);
        product.setProductType((Set<String>) productType);
        product.setProductState(new AvailableState());
        return product;
    }

    // Helper method to set up the mock repository behavior
    private void setupMockRepository(List<Product> products) {
        when(productRepository.findAll()).thenReturn(products);
    }

    @Test
    void givenProductsWhenFilteringByType() {
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 1", "Description 1", "image1.jpg", 10, 100.0, 10.0, new HashSet<>(Arrays.asList("Furniture", "Living Room") )));
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 2", "Description 2", "image2.jpg", 10, 150.0, 10.0, new HashSet<>(Arrays.asList("Bedroom", "Kitchen"))));
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 3", "Description 3", "image3.jpg", 10, 80.0, 20.0, new HashSet<>(Arrays.asList("Furniture", "Kitchen"))));

        setupMockRepository(mockProducts);
        
        List<Product> filteredProducts = productTypeSearchStrategy.filterProducts();

        assertEquals(1, filteredProducts.size());
        assertEquals("Product 1", filteredProducts.get(0).getProductName());
    }

    @Test
    void givenNoMatchingProducts() {
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 1", "Description 1", "image1.jpg", 3, 100.0, 10.0, new HashSet<>(Arrays.asList("kitchen", "Living Room") )));
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 2", "Description 2", "image2.jpg", 10, 150.0, 10.0, new HashSet<>(Arrays.asList("Bedroom", "Kitchen"))));
        mockProducts.add(createProduct(UUID.randomUUID(), "Product 3", "Description 3", "image3.jpg", 10, 80.0, 10.0, new HashSet<>(Arrays.asList("Furniture", "Kitchen"))));

        setupMockRepository(mockProducts);
        List<Product> filteredProducts = productTypeSearchStrategy.filterProducts();

        assertEquals(0, filteredProducts.size());
    }
}

