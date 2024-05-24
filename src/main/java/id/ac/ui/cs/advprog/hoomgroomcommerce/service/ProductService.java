package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(Product product, MultipartFile file);
    List<Product> findAll();
    List<Product> findByFilter(SearchStrategy productStrategy);
    Product findById(UUID productId);
    Product editProduct(Product editedProduct, MultipartFile file);
    void deleteProduct(UUID productId);

}
