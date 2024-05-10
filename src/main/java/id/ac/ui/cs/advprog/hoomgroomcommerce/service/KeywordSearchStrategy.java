package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class KeywordSearchStrategy implements SearchStrategy {

    private ProductRepository productRepository;
    private ArrayList<Product> productsByKeyword = new ArrayList<>();
    private ArrayList<Product> productsDescByKeyword = new ArrayList<>();

    public KeywordSearchStrategy(ProductRepository productRepository, String keyword) {
        this.productRepository = productRepository;
        this.productsByKeyword = new ArrayList<>(productRepository.findByProductNameContainingIgnoreCase(keyword));
        this.productsDescByKeyword = new ArrayList<>(productRepository.findByProductDescriptionContainingIgnoreCase(keyword));
        this.productsByKeyword.addAll(productsDescByKeyword);
    }

    @Override
    public List<Product> filterProducts() {
        return productsByKeyword.stream()
                .distinct()
                .sorted(Comparator.comparing(Product::getProductName)
                        .thenComparing(Product::getProductDescription))
                .collect(Collectors.toList());
    }
}