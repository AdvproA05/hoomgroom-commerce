package id.ac.ui.cs.advprog.hoomgroomcommerce.repository;


import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Iterator;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;




@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {


    @InjectMocks
    ProductRepository productRepository;


    @BeforeEach
    void setUp() {
    }


    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId(UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"));
        product.setProductName("Sofa Fabric");
        product.setProductDescription("This is a comfortable sofa made of high-quality fabric.");
        product.setProductImage("https://example.com/sofa_fabric_image.jpg");
        product.setProductQuantity(50);
        product.setProductPrice(50000.0);
        product.setProductDiscountPrice(40000.0);
        productRepository.create(product);


        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductDescription(), savedProduct.getProductDescription());
        assertEquals(product.getProductImage(), savedProduct.getProductImage());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
        assertEquals(product.getProductPrice(), savedProduct.getProductPrice());
        assertEquals(product.getProductDiscountPrice(), savedProduct.getProductDiscountPrice());
    }


    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }


    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId(UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"));
        product1.setProductName("Sofa Fabric");
        product1.setProductDescription("This is a comfortable sofa made of high-quality fabric.");
        product1.setProductImage("https://example.com/sofa_fabric_image.jpg");
        product1.setProductQuantity(50);
        product1.setProductPrice(50000.0);
        product1.setProductDiscountPrice(40000.0);
        productRepository.create(product1);


        Product product2 = new Product();
        product2.setProductId(UUID.fromString("857b3c84-8eab-4296-8ca9-6773ffd86517"));
        product2.setProductName("Dining Table");
        product2.setProductDescription("A dining table with a minimalist design, perfect for your dining room.");
        product2.setProductImage("https://example.com/dining_table_image.jpg");
        product2.setProductQuantity(30);
        product2.setProductPrice(1000000.0);
        product2.setProductDiscountPrice(900000.0);
        productRepository.create(product2);


        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());


    }


    @Test
    void testEditProduct(){
        Product product = new Product();
        product.setProductId(UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"));
        product.setProductName("Sofa Fabric");
        product.setProductDescription("This is a comfortable sofa made of high-quality fabric.");
        product.setProductImage("https://example.com/sofa_fabric_image.jpg");
        product.setProductQuantity(50);
        product.setProductPrice(50000.0);
        product.setProductDiscountPrice(40000.0);
        productRepository.create(product);


        Product editedProduct = new Product();
        editedProduct.setProductId(UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"));
        editedProduct.setProductName("Sofa Leather");
        editedProduct.setProductDescription("This is a comfortable sofa made of high-quality Leather.");
        editedProduct.setProductImage("https://example.com/sofa_Leather_image.jpg");
        editedProduct.setProductQuantity(40);
        editedProduct.setProductPrice(70000.0);
        editedProduct.setProductDiscountPrice(40000.0);
        productRepository.edit(editedProduct);


        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(editedProduct.getProductId(),savedProduct.getProductId());
        assertEquals(editedProduct.getProductName(), savedProduct.getProductName());
        assertEquals(editedProduct.getProductDescription(),savedProduct.getProductDescription());
        assertEquals(editedProduct.getProductImage(),savedProduct.getProductImage());
        assertEquals(editedProduct.getProductQuantity(),savedProduct.getProductQuantity());
        assertEquals(editedProduct.getProductPrice(),savedProduct.getProductPrice());
        assertEquals(editedProduct.getProductDiscountPrice(),savedProduct.getProductDiscountPrice());
    }


    @Test
    void testEditProductWithMoreThanOneItem(){
        Product product1 = new Product();
        product1.setProductId(UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"));
        product1.setProductName("Sofa Fabric");
        product1.setProductDescription("This is a comfortable sofa made of high-quality fabric.");
        product1.setProductImage("https://example.com/sofa_fabric_image.jpg");
        product1.setProductQuantity(50);
        product1.setProductPrice(50000.0);
        product1.setProductDiscountPrice(40000.0);
        productRepository.create(product1);


        Product product2 = new Product();
        product2.setProductId(UUID.fromString("857b3c84-8eab-4296-8ca9-6773ffd86517"));
        product2.setProductName("Dining Table");
        product2.setProductDescription("A dining table with a minimalist design, perfect for your dining room.");
        product2.setProductImage("https://example.com/dining_table_image.jpg");
        product2.setProductQuantity(40);
        product2.setProductPrice(1000000.0);
        product2.setProductDiscountPrice(900000.0);
        productRepository.create(product2);


        Product editedProduct = new Product();
        editedProduct.setProductId(UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"));
        editedProduct.setProductName("Sofa Leather");
        editedProduct.setProductDescription("This is a comfortable sofa made of high-quality Leather.");
        editedProduct.setProductImage("https://example.com/sofa_Leather_image.jpg");
        editedProduct.setProductQuantity(40);
        editedProduct.setProductPrice(70000.0);
        editedProduct.setProductDiscountPrice(40000.0);
        productRepository.edit(editedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(editedProduct.getProductId(),savedProduct.getProductId());
        assertEquals(editedProduct.getProductName(), savedProduct.getProductName());
        assertEquals(editedProduct.getProductDescription(),savedProduct.getProductDescription());
        assertEquals(editedProduct.getProductImage(),savedProduct.getProductImage());
        assertEquals(editedProduct.getProductQuantity(),savedProduct.getProductQuantity());
        assertEquals(editedProduct.getProductPrice(),savedProduct.getProductPrice());
        assertEquals(editedProduct.getProductDiscountPrice(),savedProduct.getProductDiscountPrice());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(),savedProduct.getProductId());
        assertEquals(product2.getProductName(), savedProduct.getProductName());
        assertEquals(product2.getProductDescription(),savedProduct.getProductDescription());
        assertEquals(product2.getProductImage(),savedProduct.getProductImage());
        assertEquals(product2.getProductQuantity(),savedProduct.getProductQuantity());
        assertEquals(product2.getProductPrice(),savedProduct.getProductPrice());
        assertEquals(product2.getProductDiscountPrice(),savedProduct.getProductDiscountPrice());
    }

    @Test
    void testEditProductNotFound() {
        Product editedProduct = new Product();
        editedProduct.setProductId(UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"));
        editedProduct.setProductName("Sofa Leather");
        editedProduct.setProductDescription("This is a comfortable sofa made of high-quality Leather.");
        editedProduct.setProductImage("https://example.com/sofa_Leather_image.jpg");
        editedProduct.setProductQuantity(40);
        editedProduct.setProductPrice(70000.0);
        editedProduct.setProductDiscountPrice(40000.0);

        assertThrows(IllegalArgumentException.class, () ->
                productRepository.edit(editedProduct));
    }

    @Test
    void testEditProductWithNullId() {
        Product editedProduct = new Product();
        editedProduct.setProductName("Sofa Leather");
        editedProduct.setProductDescription("This is a comfortable sofa made of high-quality Leather.");
        editedProduct.setProductImage("https://example.com/sofa_Leather_image.jpg");
        editedProduct.setProductQuantity(40);
        editedProduct.setProductPrice(70000.0);
        editedProduct.setProductDiscountPrice(40000.0);

        assertThrows(IllegalArgumentException.class, () ->
                productRepository.edit(editedProduct));
    }

    @Test
    void testDeleteProduct(){
        Product product = new Product();
        product.setProductId(UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"));
        product.setProductName("Sofa Leather");
        product.setProductDescription("This is a comfortable sofa made of high-quality Leather.");
        product.setProductImage("https://example.com/sofa_Leather_image.jpg");
        product.setProductQuantity(40);
        product.setProductPrice(70000.0);
        product.setProductDiscountPrice(40000.0);
        productRepository.create(product);

        Product deletedProduct = productRepository.delete(product.getProductId());
        assertEquals(product, deletedProduct);

        Iterator<Product> productIterator  = productRepository.findAll();
        assertFalse(productIterator.hasNext());

    }

    @Test
    void testDeleteProductWithMoreThanOneItem(){
        Product product1 = new Product();
        product1.setProductId(UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104"));
        product1.setProductName("Sofa Fabric");
        product1.setProductDescription("This is a comfortable sofa made of high-quality fabric.");
        product1.setProductImage("https://example.com/sofa_fabric_image.jpg");
        product1.setProductQuantity(50);
        product1.setProductPrice(50000.0);
        product1.setProductDiscountPrice(40000.0);
        productRepository.create(product1);


        Product product2 = new Product();
        product2.setProductId(UUID.fromString("857b3c84-8eab-4296-8ca9-6773ffd86517"));
        product2.setProductName("Dining Table");
        product2.setProductDescription("A dining table with a minimalist design, perfect for your dining room.");
        product2.setProductImage("https://example.com/dining_table_image.jpg");
        product2.setProductQuantity(40);
        product2.setProductPrice(1000000.0);
        product2.setProductDiscountPrice(900000.0);
        productRepository.create(product2);

        Product deletedProduct = productRepository.delete(product1.getProductId());
        assertEquals(product1, deletedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product2.getProductId(),savedProduct.getProductId());
        assertEquals(product2.getProductName(), savedProduct.getProductName());
        assertEquals(product2.getProductDescription(),savedProduct.getProductDescription());
        assertEquals(product2.getProductImage(),savedProduct.getProductImage());
        assertEquals(product2.getProductQuantity(),savedProduct.getProductQuantity());
        assertEquals(product2.getProductPrice(),savedProduct.getProductPrice());
        assertEquals(product2.getProductDiscountPrice(),savedProduct.getProductDiscountPrice());
    }

    @Test
    void testDeleteProductWithNullId() {
        assertThrows(IllegalArgumentException.class, () ->
                productRepository.delete(null));
    }

    @Test
    void testDeleteProductNotFound() {
        assertThrows(IllegalArgumentException.class, () ->
                productRepository.delete(UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104")));
    }

    @Test
    void testFindById() {
        Product product = new Product();
        UUID productId = UUID.fromString("6f1238f8-d13a-4e5b-936f-e55156158104");
        product.setProductId(productId);
        product.setProductName("Sofa Fabric");
        product.setProductDescription("This is a comfortable sofa made of high-quality fabric.");
        product.setProductImage("https://example.com/sofa_fabric_image.jpg");
        product.setProductQuantity(50);
        product.setProductPrice(50000.0);
        product.setProductDiscountPrice(40000.0);
        productRepository.create(product);

        Product foundProduct = productRepository.findById(productId);

        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getProductId());
        assertEquals("Sofa Fabric", foundProduct.getProductName());
        assertEquals("This is a comfortable sofa made of high-quality fabric.", foundProduct.getProductDescription());
        assertEquals("https://example.com/sofa_fabric_image.jpg", foundProduct.getProductImage());
        assertEquals(50, foundProduct.getProductQuantity());
        assertEquals(50000.0, foundProduct.getProductPrice());
        assertEquals(40000.0, foundProduct.getProductDiscountPrice());
    }

    @Test
    void testFindByIdNotFound() {
        UUID nonExistentId = UUID.fromString("857b3c84-8eab-4296-8ca9-6773ffd86517");
        assertThrows(IllegalArgumentException.class, () ->
                productRepository.findById(nonExistentId)
        );
    }




}
