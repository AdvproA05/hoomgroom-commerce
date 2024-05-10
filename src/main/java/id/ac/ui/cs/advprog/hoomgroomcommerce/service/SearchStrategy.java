package id.ac.ui.cs.advprog.hoomgroomcommerce.service;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import java.util.List;

public interface SearchStrategy {
    List<Product> filterProducts();
}
