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
public class DiscountSearchStrategyTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DiscountSearchStrategy discountSearchStrategy;

    private List<Product> mockProducts;

    @BeforeEach
    void setUp() {
        mockProducts = new ArrayList<>();
    }

    // Metode untuk membuat produk dengan harga diskon
    Product createProduct(UUID productId, String productName, String productDescription, String productImage, int productQuantity, Double productPrice, Double productDiscountPrice){
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

    @Test
    void testFilterProductsNoDiscountedProducts() {
        // Tambahkan beberapa produk dengan diskon
        Product discountedProduct1 = createProduct(UUID.randomUUID(), "Product 1", "Description 1", "image1.jpg", 10, 100.0, 0.0);
        Product discountedProduct2 = createProduct(UUID.randomUUID(), "Product 2", "Description 2", "image2.jpg", 20, 200.0, 0.0);
        mockProducts.add(discountedProduct1);
        mockProducts.add(discountedProduct2);

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> filteredProducts = discountSearchStrategy.filterProducts();

        assertEquals(0, filteredProducts.size());
    }

    @Test
    void testFilterProductsWithDiscountedProducts() {
        // Tambahkan beberapa produk dengan diskon
        Product discountedProduct1 = createProduct(UUID.randomUUID(), "Product 1", "Description 1", "image1.jpg", 10, 100.0, 10.0);
        Product discountedProduct2 = createProduct(UUID.randomUUID(), "Product 2", "Description 2", "image2.jpg", 20, 200.0, 20.0);
        mockProducts.add(discountedProduct1);
        mockProducts.add(discountedProduct2);

        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> filteredProducts = discountSearchStrategy.filterProducts();

        assertEquals(2, filteredProducts.size());

    }
}

