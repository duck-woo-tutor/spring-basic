package io.myselectshop.config;

import io.myselectshop.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductRepository productRepository() {
        String dbUrl = "jdbc:h2:mem:test";
        String username = "sa";
        String password = "";
        return new ProductRepository(dbUrl, username, password);
    }
}
