package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PriceMaxSearchStrategy implements SearchStrategy {
    private ProductRepository productRepository;
    private double maxPrice;

    public PriceMaxSearchStrategy(ProductRepository productRepository, double maxPrice) {
        this.productRepository = productRepository;
        this.maxPrice = maxPrice;
    }

    public List<Product> filterProducts() {
        return productRepository.findAll().stream()
            .filter(product -> {
                Double discountedPrice = product.getProductPrice() - product.getProductDiscountPrice();
                return discountedPrice <= maxPrice;
            })
            .sorted(Comparator.comparingDouble((Product product) -> product.getProductPrice() - product.getProductDiscountPrice()).reversed())
            .collect(Collectors.toList());
    }

}
