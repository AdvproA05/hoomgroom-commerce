package id.ac.ui.cs.advprog.hoomgroomcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}