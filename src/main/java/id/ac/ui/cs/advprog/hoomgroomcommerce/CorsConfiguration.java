package id.ac.ui.cs.advprog.hoomgroomcommerce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "https://hoomgroom-frontend.vercel.app/") // Allow requests from this origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow these methods
                        .allowCredentials(true); // Allow cookies
            }
        };
    }
}
