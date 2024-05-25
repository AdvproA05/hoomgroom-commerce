package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Product;
import id.ac.ui.cs.advprog.hoomgroomcommerce.repository.ProductRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import id.ac.ui.cs.advprog.hoomgroomcommerce.service.CloudinaryService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CloudinaryService cloudinary = new CloudinaryService();

    @Override
    public Product createProduct(Product product, MultipartFile file) {
        String url = uploadImage(file);
        product.setProductImage(url);
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(UUID productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElse(null);
    }

    public List<Product> findByFilter(SearchStrategy productStrategy){
        return productStrategy.filterProducts();
    }

    @Override
    public Product editProduct(Product editedProduct, MultipartFile file) {
        String url = uploadImage(file);
        editedProduct.setProductImage(url);
        return productRepository.save(editedProduct);
    }

    @Override
    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }

    private String uploadImage(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.getCloudinary().uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
