package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PriceMinSearchStrategy implements SearchStrategy {
    private ProductRepository productRepository;
    private double minPrice;

    public PriceMinSearchStrategy(ProductRepository productRepository, double minPrice) {
        this.productRepository = productRepository;
        this.minPrice = minPrice;
    }

    @Override
    public List<Product> filterProducts() {
        return productRepository.findAll().stream()
                .filter(product -> {
                    Double discountedPrice = product.getProductPrice() - product.getProductDiscountPrice();
                    return  discountedPrice >= minPrice;
                })
                .sorted(Comparator.comparingDouble(product -> product.getProductPrice() - product.getProductDiscountPrice()))
                .collect(Collectors.toList());
    }
}
