package id.ac.ui.cs.advprog.hoomgroomcommerce.controller;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.when;


class ProductControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        when(productService.createProduct(product)).thenReturn(product);

        CompletableFuture<ResponseEntity<Product>> response = productController.createProduct(product);

        try{
        assertEquals(HttpStatus.CREATED, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        verify(productService, times(1)).createProduct(product);
    }

    @Test
    void testUpdateProduct() {
        UUID id = UUID.randomUUID();
        Product product = new Product();
        when(productService.findById(id)).thenReturn(product);
        when(productService.editProduct(product)).thenReturn(product);

        CompletableFuture<ResponseEntity<Product>> response = productController.updateProduct(id, product);

        try{
        assertEquals(HttpStatus.OK, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        verify(productService, times(1)).editProduct(product);
    }

    @Test
    void testDeleteProduct() {
        UUID id = UUID.randomUUID();

        CompletableFuture<ResponseEntity<Void>> response = productController.deleteProduct(id);

        try {
            assertEquals(HttpStatus.NO_CONTENT, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        verify(productService, times(1)).deleteProduct(id);
    }

    @Test
    public void testGetProduct()  {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        product.setProductId(productId);

        when(productService.findById(productId)).thenReturn(product);

        CompletableFuture<ResponseEntity<Product>> response = productController.getProduct(productId);

        try {
            assertEquals(HttpStatus.OK, response.get().getStatusCode());
            assertEquals(product, response.get().getBody());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        verify(productService, times(1)).findById(productId);
    }

    @Test
    void testGetAllProduct() {
        List<Product> products = Arrays.asList(new Product(), new Product());

        when(productService.findAll()).thenReturn(products);

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getAllProduct();

        try {
            assertEquals(HttpStatus.OK, response.get().getStatusCode());
            assertEquals(products, response.get().getBody());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        verify(productService, times(1)).findAll();
    }

    @Test
    public void testGetByDiscountProduct() {
        List<Product> products = Arrays.asList(new Product(), new Product());

        SearchStrategy strategy = new DiscountSearchStrategy(productRepository);
        when(productService.findByFilter(any(DiscountSearchStrategy.class))).thenAnswer(i -> products);

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getByDiscountProduct();

        try {
            Thread.sleep(1000);
            assertEquals(HttpStatus.OK, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        verify(productService, times(1)).findByFilter(any(SearchStrategy.class));
    }

    @Test
    public void testGetByKeywordProduct() {
        String keyword = "example";
        List<Product> products = Arrays.asList(new Product(), new Product());

        SearchStrategy strategy = new KeywordSearchStrategy(productRepository, keyword);
        when(productService.findByFilter(strategy)).thenAnswer(i -> CompletableFuture.completedFuture(products));

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getByKeywordProduct(keyword);

        try {
            assertEquals(HttpStatus.OK, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        verify(productService, times(1)).findByFilter(any(SearchStrategy.class));
    }

    @Test
    public void testGetByMaxPriceProduct()  {
        double max = 100.0;
        List<Product> products = Arrays.asList(new Product(), new Product());

        SearchStrategy strategy = new PriceMaxSearchStrategy(productRepository, max);
        when(productService.findByFilter(strategy)).thenAnswer(i -> CompletableFuture.completedFuture(products));

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getByMaxPriceProduct(max);

        try {
            assertEquals(HttpStatus.OK, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        verify(productService, times(1)).findByFilter(any(SearchStrategy.class));
    }

    @Test
    public void testGetByMinPriceProduct()  {
        double min = 10.0;
        List<Product> products = Arrays.asList(new Product(), new Product());

        SearchStrategy strategy = new PriceMinSearchStrategy(productRepository, min);
        when(productService.findByFilter(strategy)).thenAnswer(i -> CompletableFuture.completedFuture(products));

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getByMinPriceProduct(min);

        try {
            assertEquals(HttpStatus.OK, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        verify(productService, times(1)).findByFilter(any(SearchStrategy.class));
    }

    @Test
    public void testGetByRangePriceProduct() {
        double min = 10.0;
        double max = 100.0;
        List<Product> products = Arrays.asList(new Product(), new Product());

        SearchStrategy strategy = new PriceRangeSearchStrategy(productRepository, min, max);
        when(productService.findByFilter(strategy)).thenAnswer(i -> CompletableFuture.completedFuture(products));

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getByRangePriceProduct(min, max);

        try {
            assertEquals(HttpStatus.OK, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        verify(productService, times(1)).findByFilter(any(SearchStrategy.class));
    }

    @Test
    public void testGetByProductType()  {
        ArrayList<String> types = new ArrayList<>(Arrays.asList("type1", "type2"));
        List<Product> products = Arrays.asList(new Product(), new Product());

        SearchStrategy strategy = new ProductTypeSearchStrategy(productRepository, types);
        when(productService.findByFilter(strategy)).thenAnswer(i -> CompletableFuture.completedFuture(products));

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getByProductType(types);

        try {
            assertEquals(HttpStatus.OK, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        verify(productService, times(1)).findByFilter(any(SearchStrategy.class));
    }
}
