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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KeywordSearchStrategyTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private KeywordSearchStrategy keywordSearchStrategy;

    private List<Product> mockProducts;

    @BeforeEach
    void setUp() {
        mockProducts = new ArrayList<>();
    }

    // Helper method to create a product
    Product createProduct(UUID productId, String productName, String productDescription, String productImage, int productQuantity, Double productPrice, Double productDiscountPrice) {
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

    @Test
    void givenProductsWhenKeywordNotFoundInNameAndDescription() {
        // Arrange
        ArrayList<Product> products = new ArrayList<>();
        when(productRepository.findByProductNameContainingIgnoreCase("Nonexistent")).thenReturn(new ArrayList<>());
        when(productRepository.findByProductDescriptionContainingIgnoreCase("Nonexistent")).thenReturn(new ArrayList<>());

        // Act
        keywordSearchStrategy = new KeywordSearchStrategy(productRepository,"Nonexistent");
        List<Product> filteredProducts = keywordSearchStrategy.filterProducts();

        // Assert
        assertEquals(0, filteredProducts.size());
    }

    @Test
    void givenProductsWhenKeywordFoundInName() {
        // Arrange
        ArrayList<Product> products = new ArrayList<>();
        products.add(createProduct(UUID.randomUUID(), "Product 1", "Description 1", "image1.jpg", 10, 100.0, 0.0));
        products.add(createProduct(UUID.randomUUID(), "Keyword Product 2", "Description 2", "image2.jpg", 10, 100.0, 0.0));
        products.add(createProduct(UUID.randomUUID(), "Product 3", "Description 3", "image3.jpg", 10, 100.0, 0.0));
        when(productRepository.findByProductNameContainingIgnoreCase("Keyword")).thenReturn(new ArrayList<>(List.of(products.get(1))));
        when(productRepository.findByProductDescriptionContainingIgnoreCase("Keyword")).thenReturn(new ArrayList<>());

        // Act
        keywordSearchStrategy = new KeywordSearchStrategy(productRepository, "Keyword");
        List<Product> filteredProducts = keywordSearchStrategy.filterProducts();

        // Assert
        assertEquals(1, filteredProducts.size());
        assertEquals("Keyword Product 2", filteredProducts.get(0).getProductName());
    }

    @Test
    void givenProductsWhenKeywordFoundInDescription() {
        // Arrange
        ArrayList<Product> products = new ArrayList<>();
        Product product1 = createProduct(UUID.randomUUID(), "Product 1", "Description 1", "image1.jpg", 10, 100.0, 0.0);
        Product product2 = createProduct(UUID.randomUUID(), "Product 2", "Keyword Description 2", "image2.jpg", 10, 100.0, 0.0);
        Product product3 = createProduct(UUID.randomUUID(), "Product 3", "Keyword Description 3", "image3.jpg", 10, 100.0, 0.0);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        ArrayList<Product> productsWithKeywordInDescription = new ArrayList<>();
        productsWithKeywordInDescription.add(product2);
        productsWithKeywordInDescription.add(product3);
        when(productRepository.findByProductNameContainingIgnoreCase("Keyword")).thenReturn(new ArrayList<>());
        when(productRepository.findByProductDescriptionContainingIgnoreCase("Keyword")).thenReturn(productsWithKeywordInDescription);

        // Act
        keywordSearchStrategy = new KeywordSearchStrategy(productRepository, "Keyword");
        List<Product> filteredProducts = keywordSearchStrategy.filterProducts();

        // Assert
        assertEquals(2, filteredProducts.size());
        assertEquals("Product 2", filteredProducts.get(0).getProductName());
        assertEquals("Product 3", filteredProducts.get(1).getProductName());
    }

    @Test
    void givenProductsWhenKeywordFoundInNameAndDescription() {
        // Arrange
        ArrayList<Product> products = new ArrayList<>();
        products.add(createProduct(UUID.randomUUID(), "Product 1", "Description 1", "image1.jpg", 10, 100.0, 0.0));
        products.add(createProduct(UUID.randomUUID(), "Keyword Product 2", "Keyword Description 2", "image2.jpg", 10, 100.0, 0.0));
        products.add(createProduct(UUID.randomUUID(), "Product 3", "Description 3", "image3.jpg", 10, 100.0, 0.0));
        when(productRepository.findByProductNameContainingIgnoreCase("Keyword")).thenReturn(new ArrayList<>(List.of(products.get(1))));
        when(productRepository.findByProductDescriptionContainingIgnoreCase("Keyword")).thenReturn(new ArrayList<>(List.of(products.get(1))));

        // Act
        keywordSearchStrategy = new KeywordSearchStrategy(productRepository, "Keyword");
        List<Product> filteredProducts = keywordSearchStrategy.filterProducts();

        // Assert
        assertEquals(1, filteredProducts.size());
        assertEquals("Keyword Product 2", filteredProducts.get(0).getProductName());
    }
}