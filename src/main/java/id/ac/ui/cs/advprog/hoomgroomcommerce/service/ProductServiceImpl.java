package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(UUID productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }

    public List<Product> findByFilter(SearchStrategy productStrategy){
        return productStrategy.filterProducts();
    }

    @Override
    public Product editProduct(Product editedProduct) {
        return productRepository.save(editedProduct);
    }

    @Override
    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }

}
