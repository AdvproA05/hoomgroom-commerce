package id.ac.ui.cs.advprog.hoomgroomcommerce.service;


import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ProductTypeSearchStrategy implements SearchStrategy {
    private ProductRepository productRepository;
    private ArrayList<String> types;


    public ProductTypeSearchStrategy(ProductRepository productRepository, ArrayList<String> types) {
        this.productRepository = productRepository;
        this.types = types;
    }


    @Override
    public List<Product> filterProducts() {
        return productRepository.findAll().stream()
                .filter(product -> product.getProductType().containsAll(types))
                .collect(Collectors.toList());
    }
}

