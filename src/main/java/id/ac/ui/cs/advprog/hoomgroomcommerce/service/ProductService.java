package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> findAll();
    Product findById(UUID productId);
    Product editProduct(Product editedProduct);
    Product deleteProduct(UUID productId);
}
