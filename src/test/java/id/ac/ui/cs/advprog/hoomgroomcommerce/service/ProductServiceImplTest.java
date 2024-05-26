package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    private CloudinaryService cloudinaryService;
    private Cloudinary cloudinary; // Declare the Cloudinary variable

    @BeforeEach
    void setUp(){
        cloudinaryService = new CloudinaryService();
        cloudinary = mock(Cloudinary.class);
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
        product.setProductType(productType);
        product.setProductState(new AvailableState());
        return product;
    }

    @Test
    void testCreateProduct() throws IOException {
        Product product = new Product();

        Product savedProduct = productServiceImpl.createProduct(product, null);

        verify(productRepository).save(product);
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
                new HashSet<>(Arrays.asList("Bedroom", "Kitchen")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                30000.0,
                new HashSet<>(Arrays.asList("Bedroom", "Kitchen")));

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
                new HashSet<>(Arrays.asList("Bedroom", "Kitchen")));

        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
        Product foundProduct = productServiceImpl.findById(product.getProductId());
        assertEquals(product, foundProduct);
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    void testEditProduct() {
        // Create the original product
        Product originalProduct = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"),
                "Sofa Fabric",
                "This is a comfortable sofa made of high-quality fabric.",
                "https://example.com/sofa_fabric_image.jpg",
                50,
                50000.0,
                40000.0,
                new HashSet<>(Arrays.asList("Bedroom", "Kitchen")));

        // Mock the behavior of the product repository save method
        when(productRepository.save(any())).thenReturn(originalProduct);

        // Create a modified product object with updated attributes
        Product modifiedProduct = new Product();
        modifiedProduct.setProductId(originalProduct.getProductId());
        modifiedProduct.setProductName("Updated Sofa Fabric");
        modifiedProduct.setProductDescription("This is an updated description.");
        modifiedProduct.setProductImage("https://example.com/updated_image.jpg");
        modifiedProduct.setProductQuantity(60L);
        modifiedProduct.setProductPrice(55000.0);
        modifiedProduct.setProductDiscountPrice(45000.0);
        modifiedProduct.setProductType(new HashSet<>(Arrays.asList("Living Room", "Bedroom")));

        // Call the editProduct method
        Product editedProduct = productServiceImpl.editProduct(modifiedProduct, null);

        // Verify that the product repository save method is called with the modified product
        verify(productRepository, times(1)).save(modifiedProduct);
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
                new HashSet<>(Arrays.asList("Bedroom", "Kitchen")));

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
                new HashSet<>(Arrays.asList("Bedroom", "Kitchen")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                0.0,
                new HashSet<>(Arrays.asList("Bedroom", "Kitchen")));

        Product product3 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158106"),
                "Sofa Wooden",
                "This is a comfortable sofa made of high-quality wood.",
                "https://example.com/sofa_wooden_image.jpg",
                20,
                60000.0,
                50000.0,
                new HashSet<>(Arrays.asList("Bedroom", "Kitchen")));

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
                new HashSet<>(Arrays.asList("Furniture", "Living Room")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                30000.0,
                new HashSet<>(Arrays.asList("Furniture", "Leather", "Living Room")));

        Product product3 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158106"),
                "Table Wooden",
                "This is a wooden dining table.",
                "https://example.com/table_wooden_image.jpg",
                20,
                60000.0,
                50000.0,
                new HashSet<>(Arrays.asList("Furniture", "Dining Room")));

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
                new HashSet<>(Arrays.asList("Furniture", "Living Room")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                30000.0,
                new HashSet<>(Arrays.asList("Furniture", "Living Room")));

        Product product3 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158106"),
                "Table Wooden",
                "This is a wooden dining table.",
                "https://example.com/table_wooden_image.jpg",
                20,
                60000.0,
                50000.0,
                new HashSet<>(Arrays.asList("Furniture", "Dining Room")));

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
                new HashSet<>(Arrays.asList("Furniture", "Living Room")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                30000.0,
                new HashSet<>(Arrays.asList("Furniture", "Living Room")));

        Product product3 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158106"),
                "Table Wooden",
                "This is a wooden dining table.",
                "https://example.com/table_wooden_image.jpg",
                20,
                970000.0,
                50000.0,
                new HashSet<>(Arrays.asList("Furniture", "Dining Room")));

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
                new HashSet<>(Arrays.asList("Furniture", "Living Room")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                30000.0,
                new HashSet<>(Arrays.asList("Furniture", "Living Room")));

        Product product3 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158106"),
                "Table Wooden",
                "This is a wooden dining table.",
                "https://example.com/table_wooden_image.jpg",
                20,
                60000.0,
                50000.0,
                new HashSet<>(Arrays.asList("Furniture", "Dining Room")));

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
                new HashSet<>(Arrays.asList("Furniture", "Living Room")));

        Product product2 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158105"),
                "Sofa Leather",
                "This is a comfortable sofa made of high-quality leather.",
                "https://example.com/sofa_leather_image.jpg",
                30,
                40000.0,
                30000.0,
                new HashSet<>(Arrays.asList("Furniture", "Living Room")));

        Product product3 = createProduct(
                UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158106"),
                "Table Wooden",
                "This is a wooden dining table.",
                "https://example.com/table_wooden_image.jpg",
                20,
                60000.0,
                50000.0,
                new HashSet<>(Arrays.asList("Furniture", "Dining Room")));

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
