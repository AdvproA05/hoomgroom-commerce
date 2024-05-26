package id.ac.ui.cs.advprog.hoomgroomcommerce.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;
import id.ac.ui.cs.advprog.hoomgroomcommerce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CompletableFuture<ResponseEntity<Product>> createProduct(@RequestPart("product") String productStr, @RequestPart("file") MultipartFile file) throws IOException, JsonProcessingException {
        Product product = new ObjectMapper().readValue(productStr, Product.class);
        Product createdProduct = service.createProduct(product, file);
        return CompletableFuture.completedFuture(new ResponseEntity<>(createdProduct, HttpStatus.CREATED));
    }

    @Async
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity<Product>> updateProduct(@PathVariable UUID id, @RequestBody Product product, @RequestParam("file") MultipartFile file) {
        Product existingProduct = service.findById(id);
        if (existingProduct == null) {
            return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductDescription(product.getProductDescription());
        existingProduct.setProductImage(product.getProductImage());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setProductDiscountPrice(product.getProductDiscountPrice());
        Product updatedProduct = service.editProduct(existingProduct, file);
        return CompletableFuture.completedFuture(new ResponseEntity<>(updatedProduct, HttpStatus.OK));
    }

    @Async
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteProduct(@PathVariable UUID id) {
        service.deleteProduct(id);
        return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }


    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Product>> getProduct(@PathVariable UUID id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ResponseEntity.ok(service.findById(id));
            } catch (Exception e) {
                logger.error("Error in getting a product: {}", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        });
    }




    @GetMapping("/AllProduct")
    public CompletableFuture<ResponseEntity<List<Product>>> getAllProduct() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ResponseEntity.ok(service.findAll());
            } catch (Exception e) {
                logger.error("Error in getting products: {}", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        });
    }



    @GetMapping("/AllDiscountProduct")
    public CompletableFuture<ResponseEntity<List<Product>>> getByDiscountProduct() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                SearchStrategy strategy = new DiscountSearchStrategy(productRepository);
                List<Product> products = service.findByFilter(strategy);
                return ResponseEntity.ok(products);
            } catch (Exception e) {
                logger.error("Error in getting products by discount: {}", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        });
    }



    @GetMapping("/AllKeywordProduct")
    public CompletableFuture<ResponseEntity<List<Product>>> getByKeywordProduct(@RequestParam(required = true) String keyword) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                SearchStrategy strategy = new KeywordSearchStrategy(productRepository, keyword);
                List<Product> products = service.findByFilter(strategy);
                return ResponseEntity.ok(products);
            } catch (Exception e) {
                logger.error("Error in getting products by keyword: {}", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        });
    }

    @GetMapping("/AllMaxProduct")
    public CompletableFuture<ResponseEntity<List<Product>>> getByMaxPriceProduct(@RequestParam(required = true) Double max) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                SearchStrategy strategy = new PriceMaxSearchStrategy(productRepository, max);
                List<Product> products = service.findByFilter(strategy);
                return ResponseEntity.ok(products);
            } catch (Exception e) {
                logger.error("Error in getting products by maximum price: {}", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        });
    }

    @GetMapping("/AllMinProduct")
    public CompletableFuture<ResponseEntity<List<Product>>> getByMinPriceProduct(@RequestParam(required = true) Double min) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                SearchStrategy strategy = new PriceMinSearchStrategy(productRepository, min);
                List<Product> products = service.findByFilter(strategy);
                return ResponseEntity.ok(products);
            } catch (Exception e) {
                logger.error("Error in getting products by minimum price: {}", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        });
    }

    @GetMapping("/AllRangeProduct")
    public CompletableFuture<ResponseEntity<List<Product>>> getByRangePriceProduct(@RequestParam(required = true) Double min, @RequestParam(required = true) Double max) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                SearchStrategy strategy = new PriceRangeSearchStrategy(productRepository, min, max);
                List<Product> products = service.findByFilter(strategy);
                return ResponseEntity.ok(products);
            } catch (Exception e) {
                logger.error("Error in getting products by price range: {}", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        });
    }

    @GetMapping("/AllProductType")
    public CompletableFuture<ResponseEntity<List<Product>>> getByProductType(@RequestParam(required = true) ArrayList<String> types) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                SearchStrategy strategy = new ProductTypeSearchStrategy(productRepository, types);
                List<Product> products = service.findByFilter(strategy);
                return ResponseEntity.ok(products);
            } catch (Exception e) {
                logger.error("Error in getting products by type: {}", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        });
    }

    @PostMapping("/top10")
    public ResponseEntity<List<Object[]>> receiveTop10Products(@RequestBody List<Object[]> top10Products) {
        try {
            List<Object[]> productSalePairs = new ArrayList<>();
            for (Object[] productStat : top10Products) {
                UUID productId = (UUID) productStat[0];
                Long totalQuantitySold = (Long) productStat[1];
                Product product = service.findById(productId);
                if (product != null) {
                    productSalePairs.add(new Object[]{product, totalQuantitySold});
                } else {
                    return ResponseEntity.notFound().build();
                }
            }
            return ResponseEntity.ok(productSalePairs);
        } catch (Exception e) {
            logger.error("Error in processing top 10 products: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}