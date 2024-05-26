package id.ac.ui.cs.advprog.hoomgroomcommerce.controller;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

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
    void testCreateProduct() throws Exception {
        Product product = new Product();
        MultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());

        // Convert Product object to JSON String
        ObjectMapper objectMapper = new ObjectMapper();
        String productJson = objectMapper.writeValueAsString(product);

        when(productService.createProduct(any(Product.class), any(MultipartFile.class))).thenReturn(product);

        CompletableFuture<ResponseEntity<Product>> response = productController.createProduct(productJson, file);

        assertEquals(HttpStatus.CREATED, response.get().getStatusCode());
        verify(productService, times(1)).createProduct(any(Product.class), any(MultipartFile.class));
    }


    @Test
    void testUpdateProduct() throws Exception {
        UUID id = UUID.randomUUID();
        Product product = new Product();
        MultipartFile file = new MockMultipartFile("file", "updated.txt", MediaType.TEXT_PLAIN_VALUE, "Updated data".getBytes());

        when(productService.findById(id)).thenReturn(product);
        when(productService.editProduct(any(Product.class), any(MultipartFile.class))).thenReturn(product);

        CompletableFuture<ResponseEntity<Product>> response = productController.updateProduct(id, product, file);

        assertEquals(HttpStatus.OK, response.get().getStatusCode());
        verify(productService, times(1)).editProduct(any(Product.class), any(MultipartFile.class));
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

    @Test
    void testUpdateProductNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        Product product = new Product();
        MultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());

        when(productService.findById(id)).thenReturn(null);

        CompletableFuture<ResponseEntity<Product>> response = productController.updateProduct(id, product, file);

        assertEquals(HttpStatus.NOT_FOUND, response.get().getStatusCode());
        verify(productService, times(0)).editProduct(any(Product.class), any(MultipartFile.class));
    }

    @Test
    public void testGetProductException() throws Exception {
        UUID productId = UUID.randomUUID();
        when(productService.findById(productId)).thenThrow(new RuntimeException("Database error"));

        CompletableFuture<ResponseEntity<Product>> response = productController.getProduct(productId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.get().getStatusCode());
    }


    @Test
    public void testGetAllProductException() throws Exception {
        when(productService.findAll()).thenThrow(new RuntimeException("Database error"));

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getAllProduct();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.get().getStatusCode());
    }


    @Test
    public void testGetByDiscountProductException() throws Exception {
        when(productService.findByFilter(any(DiscountSearchStrategy.class))).thenThrow(new RuntimeException("Database error"));

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getByDiscountProduct();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.get().getStatusCode());
    }

    @Test
    public void testGetByKeywordProductException() {
        String keyword = "example";

        when(productService.findByFilter(any(SearchStrategy.class))).thenThrow(new RuntimeException("Database error"));

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getByKeywordProduct(keyword);

        try {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        verify(productService, times(1)).findByFilter(any(SearchStrategy.class));
    }

    @Test
    public void testGetByMaxPriceProductException() {
        double max = 100.0;

        when(productService.findByFilter(any(SearchStrategy.class))).thenThrow(new RuntimeException("Database error"));

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getByMaxPriceProduct(max);

        try {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        verify(productService, times(1)).findByFilter(any(SearchStrategy.class));
    }

    @Test
    public void testGetByMinPriceProductException() {
        double min = 10.0;

        when(productService.findByFilter(any(SearchStrategy.class))).thenThrow(new RuntimeException("Database error"));

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getByMinPriceProduct(min);

        try {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        verify(productService, times(1)).findByFilter(any(SearchStrategy.class));
    }

    @Test
    public void testGetByRangePriceProductException() {
        double min = 10.0;
        double max = 100.0;

        when(productService.findByFilter(any(SearchStrategy.class))).thenThrow(new RuntimeException("Database error"));

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getByRangePriceProduct(min, max);

        try {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        verify(productService, times(1)).findByFilter(any(SearchStrategy.class));
    }

    @Test
    public void testGetByProductTypeException() {
        ArrayList<String> types = new ArrayList<>(Arrays.asList("type1", "type2"));

        when(productService.findByFilter(any(SearchStrategy.class))).thenThrow(new RuntimeException("Database error"));

        CompletableFuture<ResponseEntity<List<Product>>> response = productController.getByProductType(types);

        try {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.get().getStatusCode());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        verify(productService, times(1)).findByFilter(any(SearchStrategy.class));
    }

    @Test
    public void testReceiveTop10ProductsException() throws Exception {
        UUID productId = UUID.randomUUID();
        Long totalQuantitySold = 100L;
        Object[] productStat = new Object[]{productId, totalQuantitySold};

        // Mock the productService.findById method to throw an exception
        when(productService.findById(productId)).thenThrow(new RuntimeException("Database error"));

        List<Object[]> top10Products = Collections.singletonList(productStat);
        String requestBody = "[{\"productId\":\"" + productId + "\",\"totalQuantitySold\":" + totalQuantitySold + "}]";

        mockMvc.perform(post("/top10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNotFound());
    }




}
