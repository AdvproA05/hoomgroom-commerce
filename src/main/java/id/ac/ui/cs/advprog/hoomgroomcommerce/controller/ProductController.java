package id.ac.ui.cs.advprog.hoomgroomcommerce.controller;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;
import id.ac.ui.cs.advprog.hoomgroomcommerce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @Async
    @PostMapping
    public CompletableFuture<ResponseEntity<Product>> createProduct(@RequestBody Product product) {
        Product createdProduct = service.createProduct(product);
        return CompletableFuture.completedFuture(new ResponseEntity<>(createdProduct, HttpStatus.CREATED));
    }

    @Async
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Product>> updateProduct(@PathVariable UUID id, @RequestBody Product product) {
        Product existingProduct = service.findById(id);
        if (existingProduct == null) {
            return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductDescription(product.getProductDescription());
        existingProduct.setProductImage(product.getProductImage());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setProductDiscountPrice(product.getProductDiscountPrice());
        Product updatedProduct = service.editProduct(existingProduct);
        return CompletableFuture.completedFuture(new ResponseEntity<>(updatedProduct, HttpStatus.OK));
    }

    @Async
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteProduct(@PathVariable UUID id) {
        service.deleteProduct(id);
        return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }



    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID id) {
        ResponseEntity responseEntity = null;
        try {
            Product product = service.findById(id);

        } catch (Exception e) {
            logger.error("Error in getting a product: {}", e.getMessage(), e);
            ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("/AllProduct")
    public ResponseEntity<List<Product>> getAllProduct() {
        ResponseEntity responseEntity = null;
        try {
            List<Product> products = service.findAll();
            responseEntity = ResponseEntity.ok(products);
        } catch (Exception e) {
            logger.error("Error in getting products: {}", e.getMessage(), e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/AllDiscountProduct")
    public ResponseEntity<List<Product>> getByDiscountProduct(){
        ResponseEntity responseEntity = null;
        try {
            SearchStrategy strategy = new DiscountSearchStrategy(productRepository);
            List<Product> products = service.findByFilter(strategy);
            responseEntity = ResponseEntity.ok(products);
        } catch (Exception e){
            logger.error("Error in getting products by discount: {}", e.getMessage(), e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;

    }

    @GetMapping("/AllKeywordProduct")
    public ResponseEntity<Product> getByKeywordProduct(@RequestParam(required = true) String keyword){
        ResponseEntity responseEntity = null;
        try {
            SearchStrategy strategy = new KeywordSearchStrategy(productRepository, keyword);
            List<Product> products = service.findByFilter(strategy);
            responseEntity = ResponseEntity.ok(products);
        } catch (Exception e){
            logger.error("Error in getting products by keyword: {}", e.getMessage(), e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }


    @GetMapping("/AllMaxProduct")
    public ResponseEntity<Product> getByMaxPriceProduct(@RequestParam(required = true) Double max){
        ResponseEntity responseEntity = null;
        try {
            SearchStrategy strategy = new PriceMaxSearchStrategy(productRepository, max);
            List<Product> products = service.findByFilter(strategy);
            responseEntity = ResponseEntity.ok(products);
        } catch (Exception e){
            logger.error("Error in getting products by maximum price: {}", e.getMessage(), e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("/AllMinProduct")
    public ResponseEntity<Product> getByMinPriceProduct(@RequestParam(required = true) Double min){
        ResponseEntity responseEntity = null;
        try {
            SearchStrategy strategy = new PriceMinSearchStrategy(productRepository, min);
            List<Product> products = service.findByFilter(strategy);
            responseEntity = ResponseEntity.ok(products);
        } catch (Exception e){
            logger.error("Error in getting products by minimum price : {}", e.getMessage(), e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("/AllRangeProduct")
    public ResponseEntity<List<Product>> getByRangePriceProduct(@RequestParam(required = true) Double min, @RequestParam(required = true) Double max) {
        try {
            SearchStrategy strategy = new PriceRangeSearchStrategy(productRepository, min, max);
            List<Product> products = service.findByFilter(strategy);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            logger.error("Error in getting products by price range: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/AlProductType")
    public ResponseEntity<Product> getByProductType(@RequestParam(required = true) ArrayList<String> types){
        ResponseEntity responseEntity = null;
        try {
            SearchStrategy strategy = new ProductTypeSearchStrategy(productRepository, types);
            List<Product> products = service.findByFilter(strategy);
            responseEntity = ResponseEntity.ok(products);
        } catch (Exception e){
            logger.error("Error in getting products by type: {}", e.getMessage(), e);
            responseEntity = ResponseEntity.badRequest().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

}