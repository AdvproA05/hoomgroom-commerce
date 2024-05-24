package id.ac.ui.cs.advprog.hoomgroomcommerce.service;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService() {
        // Replace with your actual Cloudinary credentials
        String cloudName = "dpzms9ipg";
        String apiKey = "582835946161528";
        String apiSecret = "Dsu6DyQXyFslRo9vXC682IAiSDU";

        this.cloudinary = new Cloudinary("cloudinary://" + apiKey + ":" + apiSecret + "@" + cloudName);
    }

    public Cloudinary getCloudinary() {
        return this.cloudinary;
    }
}
