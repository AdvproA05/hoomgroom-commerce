package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product createProduct(Product product) {
        productRepository.create(product);
        return product;
    }


    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }


    @Override
    public Product findById(UUID productId) {
        return productRepository.findById(productId);
    }


    @Override
    public Product editProduct(Product editedProduct) {
        productRepository.edit(editedProduct);
        return editedProduct;
    }


    @Override
    public Product deleteProduct(UUID productId) {
        return productRepository.delete(productId);
    }
}