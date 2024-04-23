package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setUp(){}

    Product createProduct(UUID productId, String productName, String productDescription, String productImage, int productQuantity, Double productPrice, Double productDiscountPrice){
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductImage(productImage);
        product.setProductQuantity(productQuantity);
        product.setProductPrice(productPrice);
        product.setProductDiscountPrice(productDiscountPrice);
        return product;
    }

    @Test
    void testCreateProduct(){
        Product product = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"),
                "Sofa Fabric",
                "This is a comfortable sofa made of high-quality fabric.",
                "https://example.com/sofa_fabric_image.jpg",
                50,
                50000.0,
                40000.0);

        when(productRepository.create(product)).thenReturn(product);
        Product savedProduct = productServiceImpl.createProduct(product);
        assertEquals(product.getProductId(), savedProduct.getProductId());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProduct(){
        List<Product> productList = new ArrayList<>();
        Product product1 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"),
                "Sofa Fabric",
                "This is a comfortable sofa made of high-quality fabric.",
                "https://example.com/sofa_fabric_image.jpg",
                50,
                50000.0,
                40000.0);

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                30000.0);

        productList.add(product1);
        productList.add(product2);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productServiceImpl.findAll();
        assertEquals(productList.size(), result.size());
        for (int i = 0; i < productList.size(); i++) {
            assertEquals(productList.get(i), result.get(i));
        }
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindProductById(){
        Product product = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"),
                "Sofa Fabric",
                "This is a comfortable sofa made of high-quality fabric.",
                "https://example.com/sofa_fabric_image.jpg",
                50,
                50000.0,
                40000.0);

        when(productRepository.findById(product.getProductId())).thenReturn(product);

        Product foundProduct = productServiceImpl.findById(product.getProductId());

        assertEquals(product, foundProduct);
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    void testEditProduct(){
        Product product = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"),
                "Sofa Fabric",
                "This is a comfortable sofa made of high-quality fabric.",
                "https://example.com/sofa_fabric_image.jpg",
                50,
                50000.0,
                40000.0);

        when(productRepository.edit(product)).thenReturn(product);

        Product editedProduct = productServiceImpl.editProduct(product);

        assertEquals(product, editedProduct);
        verify(productRepository, times(1)).edit(product);
    }

    @Test
    void testDeleteProduct(){
        Product product = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"),
                "Sofa Fabric",
                "This is a comfortable sofa made of high-quality fabric.",
                "https://example.com/sofa_fabric_image.jpg",
                50,
                50000.0,
                40000.0);

        when(productRepository.delete(product.getProductId())).thenReturn(product);
        Product deletedProduct = productServiceImpl.deleteProduct(product.getProductId());
        assertEquals(product, deletedProduct);
        verify(productRepository, times(1)).delete(product.getProductId());
    }
}
