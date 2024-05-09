package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DiscountSearchStrategy implements SearchStrategy {

    private ProductRepository productRepository;

    public DiscountSearchStrategy(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> filterProducts() {
        List<Product> allProducts = productRepository.findAll();
        allProducts.removeIf(product -> product.getProductDiscountPrice() == null || product.getProductDiscountPrice() == 0);

        Collections.sort(allProducts, Comparator.comparingDouble(Product::getProductDiscountPrice).reversed());

        return allProducts;
    }
}
