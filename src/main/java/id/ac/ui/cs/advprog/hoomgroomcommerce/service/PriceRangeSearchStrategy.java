package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PriceRangeSearchStrategy implements SearchStrategy {
    private ProductRepository productRepository;
    private double minPrice;
    private double maxPrice;

    public PriceRangeSearchStrategy(ProductRepository productRepository, double minPrice, double maxPrice) {
        this.productRepository = productRepository;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    public List<Product> filterProducts() {
        return productRepository.findAll().stream()
                .filter(product -> {
                    Double discountedPrice = product.getProductPrice() - product.getProductDiscountPrice();
                    return discountedPrice >= minPrice && discountedPrice <= maxPrice;
                })
                .sorted(Comparator.comparingDouble(product -> product.getProductPrice() - product.getProductDiscountPrice()))
                .collect(Collectors.toList());
    }
}
