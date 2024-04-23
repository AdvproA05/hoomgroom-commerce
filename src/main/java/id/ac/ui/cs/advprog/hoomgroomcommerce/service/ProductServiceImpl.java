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
        return null;
    }


    @Override
    public List<Product> findAll() {
        return null;
    }


    @Override
    public Product findById(UUID productId) {
        return null;
    }


    @Override
    public Product editProduct(Product editedProduct) {
        return null;
    }


    @Override
    public Product deleteProduct(UUID productId) {
        return null;
    }
}