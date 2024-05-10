package id.ac.ui.cs.advprog.hoomgroomcommerce.controller;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.service.ProductService;
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

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;
import id.ac.ui.cs.advprog.hoomgroomcommerce.service.ProductService;
import id.ac.ui.cs.advprog.hoomgroomcommerce.service.SearchStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void testGetProduct() throws Exception {
        UUID productId = UUID.randomUUID();
        Product product = new Product();

        when(productService.findById(productId)).thenReturn(product);
        MockHttpServletResponse response = mockMvc.perform(get("/products/" + productId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = Collections.singletonList(new Product());
        when(productService.findAll()).thenReturn(products);
        MockHttpServletResponse response = mockMvc.perform(get("/products/AllProduct")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testGetByDiscountProduct() throws Exception {
        List<Product> products = Collections.singletonList(new Product());
        when(productService.findByFilter(any(SearchStrategy.class))).thenReturn(products);
        MockHttpServletResponse response = mockMvc.perform(get("/products/AllDiscountProduct")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testGetByKeywordProduct() throws Exception {
        String keyword = "keyword";
        List<Product> products = Collections.singletonList(new Product());
        when(productService.findByFilter(any())).thenReturn(products);

        MockHttpServletResponse response = mockMvc.perform(get("/products/AllKeywordProduct")
                        .param("keyword", keyword)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    public void testGetByMaxPriceProduct() throws Exception {
        Double max = 100.0;
        List<Product> products = Collections.singletonList(new Product());
        when(productService.findByFilter(any(SearchStrategy.class))).thenReturn(products);
        MockHttpServletResponse response = mockMvc.perform(get("/products/AllMaxProduct?max=" + max)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testGetByMinPriceProduct() throws Exception {
        Double min = 50.0;
        List<Product> products = Collections.singletonList(new Product());
        when(productService.findByFilter(any(SearchStrategy.class))).thenReturn(products);
        MockHttpServletResponse response = mockMvc.perform(get("/products/AllMinProduct?min=" + min)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testGetByRangePriceProduct() throws Exception {
        Double min = 50.0;
        Double max = 100.0;
        List<Product> products = Collections.singletonList(new Product());
        when(productService.findByFilter(any(SearchStrategy.class))).thenReturn(products);
        MockHttpServletResponse response = mockMvc.perform(get("/products/AllRangeProduct?min=" + min + "&max=" + max)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testGetByProductType() throws Exception {
        ArrayList<String> types = new ArrayList<>();
        types.add("Types");
        List<Product> products = Collections.singletonList(new Product());
        when(productService.findByFilter(any(SearchStrategy.class))).thenReturn(products);
        MockHttpServletResponse response = mockMvc.perform(get("/products/AlProductType")
                        .param("types", types.get(0))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
