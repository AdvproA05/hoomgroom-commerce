package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.AvailableState;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

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

    Product createProduct(UUID productId, String productName, String productDescription, String productImage, int productQuantity, Double productPrice, Double productDiscountPrice , ArrayList<String> productType){
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setProductImage(productImage);
        product.setProductQuantity(productQuantity);
        product.setProductPrice(productPrice);
        product.setProductDiscountPrice(productDiscountPrice);
        product.setProductType(productType);
        product.setProductState(new AvailableState());
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
                40000.0,
                new ArrayList<>(Arrays.asList("Bedroom", "Kitchen")));

        when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productServiceImpl.createProduct(product);
        assertEquals(product.getProductId(), savedProduct.getProductId());
        verify(productRepository, times(1)).save(product);
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
                40000.0,
                new ArrayList<>(Arrays.asList("Bedroom", "Kitchen")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                30000.0,
                new ArrayList<>(Arrays.asList("Bedroom", "Kitchen")));

        productList.add(product1);
        productList.add(product2);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productList);
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
                40000.0,
                new ArrayList<>(Arrays.asList("Bedroom", "Kitchen")));

        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
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
                40000.0,
                new ArrayList<>(Arrays.asList("Bedroom", "Kitchen")));

        when(productRepository.save(product)).thenReturn(product);
        Product editedProduct = productServiceImpl.editProduct(product);
        assertEquals(product, editedProduct);
        verify(productRepository, times(1)).save(product);
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
                40000.0,
                new ArrayList<>(Arrays.asList("Bedroom", "Kitchen")));

        doNothing().when(productRepository).deleteById(product.getProductId());
        productServiceImpl.deleteProduct(product.getProductId());
        verify(productRepository, times(1)).deleteById(product.getProductId());
    }

    @Test
    void TestFilterByDiscount() {
        List<Product> productList = new ArrayList<>();
        Product product1 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"),
                "Sofa Fabric",
                "This is a comfortable sofa made of high-quality fabric.",
                "https://example.com/sofa_fabric_image.jpg",
                50,
                50000.0,
                40000.0,
                new ArrayList<>(Arrays.asList("Bedroom", "Kitchen")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                0.0,
                new ArrayList<>(Arrays.asList("Bedroom", "Kitchen")));

        Product product3 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158106"),
                "Sofa Wooden",
                "This is a comfortable sofa made of high-quality wood.",
                "https://example.com/sofa_wooden_image.jpg",
                20,
                60000.0,
                50000.0,
                new ArrayList<>(Arrays.asList("Bedroom", "Kitchen")));

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        when(productRepository.findAll()).thenReturn(productList);

        SearchStrategy discountSearchStrategy = new DiscountSearchStrategy(productRepository);
        List<Product> filteredProducts = productServiceImpl.findByFilter(discountSearchStrategy);

        assertEquals(2, filteredProducts.size());
        assertEquals(product3, filteredProducts.get(0));
        assertEquals(product1, filteredProducts.get(1));

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void TestFilterByKeyword() {
        List<Product> productList = new ArrayList<>();
        Product product1 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"),
                "Sofa Fabric",
                "This is a comfortable sofa made of high-quality fabric.",
                "https://example.com/sofa_fabric_image.jpg",
                50,
                50000.0,
                40000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Living Room")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                30000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Leather", "Living Room")));

        Product product3 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158106"),
                "Table Wooden",
                "This is a wooden dining table.",
                "https://example.com/table_wooden_image.jpg",
                20,
                60000.0,
                50000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Dining Room")));

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        
        when(productRepository.findByProductNameContainingIgnoreCase("Leather")).thenReturn(new ArrayList<>(List.of(productList.get(1))));
        when(productRepository.findByProductDescriptionContainingIgnoreCase("Leather")).thenReturn(new ArrayList<>(List.of(productList.get(1))));

        SearchStrategy keywordSearchStrategy = new KeywordSearchStrategy(productRepository, "Leather");
        List<Product> filteredProducts = productServiceImpl.findByFilter(keywordSearchStrategy);

        assertEquals(1, filteredProducts.size());
        assertTrue(filteredProducts.contains(product2));
        
    }

    @Test
    void TestFilterByMaxPrice(){
        List<Product> productList = new ArrayList<>();
        Product product1 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"),
                "Sofa Fabric",
                "This is a comfortable sofa made of high-quality fabric.",
                "https://example.com/sofa_fabric_image.jpg",
                50,
                100000.0,
                40000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Living Room")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                30000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Living Room")));

        Product product3 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158106"),
                "Table Wooden",
                "This is a wooden dining table.",
                "https://example.com/table_wooden_image.jpg",
                20,
                60000.0,
                50000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Dining Room")));

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        when(productRepository.findAll()).thenReturn(productList);

        SearchStrategy priceMaxSearchStrategy = new PriceMaxSearchStrategy(productRepository, 55000.0);
        List<Product> filteredProducts = productServiceImpl.findByFilter(priceMaxSearchStrategy);

        assertEquals(2, filteredProducts.size());
        assertTrue(filteredProducts.contains(product2));
        assertTrue(filteredProducts.contains(product3));

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void TestFilterByMinPrice(){
        List<Product> productList = new ArrayList<>();
        Product product1 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"),
                "Sofa Fabric",
                "This is a comfortable sofa made of high-quality fabric.",
                "https://example.com/sofa_fabric_image.jpg",
                50,
                85000.0,
                40000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Living Room")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                30000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Living Room")));

        Product product3 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158106"),
                "Table Wooden",
                "This is a wooden dining table.",
                "https://example.com/table_wooden_image.jpg",
                20,
                970000.0,
                50000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Dining Room")));

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        when(productRepository.findAll()).thenReturn(productList);

        SearchStrategy priceMinSearchStrategy = new PriceMinSearchStrategy(productRepository, 45000.0);
        List<Product> filteredProducts = productServiceImpl.findByFilter(priceMinSearchStrategy);

        assertEquals(2, filteredProducts.size());
        assertTrue(filteredProducts.contains(product1));
        assertTrue(filteredProducts.contains(product3));

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void TestFilterByRangePrice(){
        List<Product> productList = new ArrayList<>();
        Product product1 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"),
                "Sofa Fabric",
                "This is a comfortable sofa made of high-quality fabric.",
                "https://example.com/sofa_fabric_image.jpg",
                50,
                85000.0,
                40000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Living Room")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                30000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Living Room")));

        Product product3 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158106"),
                "Table Wooden",
                "This is a wooden dining table.",
                "https://example.com/table_wooden_image.jpg",
                20,
                60000.0,
                50000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Dining Room")));

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        when(productRepository.findAll()).thenReturn(productList);

        SearchStrategy priceRangeSearchStrategy = new PriceRangeSearchStrategy(productRepository, 45000.0, 55000.0);
        List<Product> filteredProducts = productServiceImpl.findByFilter(priceRangeSearchStrategy);

        assertEquals(1, filteredProducts.size());
        assertEquals(product1, filteredProducts.get(0));

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void TestFilterByProductType() {
        List<Product> productList = new ArrayList<>();
        Product product1 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"),
                "Sofa Fabric",
                "This is a comfortable sofa made of high-quality fabric.",
                "https://example.com/sofa_fabric_image.jpg",
                50,
                50000.0,
                40000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Living Room")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                30000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Living Room")));

        Product product3 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158106"),
                "Table Wooden",
                "This is a wooden dining table.",
                "https://example.com/table_wooden_image.jpg",
                20,
                60000.0,
                50000.0,
                new ArrayList<>(Arrays.asList("Furniture", "Dining Room")));

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        when(productRepository.findAll()).thenReturn(productList);

        SearchStrategy productTypeSearchStrategy = new ProductTypeSearchStrategy(productRepository, new ArrayList<>(Arrays.asList("Living Room")));
        List<Product> filteredProducts = productServiceImpl.findByFilter(productTypeSearchStrategy);

        assertEquals(2, filteredProducts.size());
        assertTrue(filteredProducts.contains(product1));
        assertTrue(filteredProducts.contains(product2));

        verify(productRepository, times(1)).findAll();
    }

}
