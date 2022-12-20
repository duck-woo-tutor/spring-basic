package io.myselectshop.service;

import io.myselectshop.dto.ProductMyPriceRequestDto;
import io.myselectshop.dto.ProductRequestDto;
import io.myselectshop.entity.Product;
import io.myselectshop.repository.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ApplicationContext context) {
        ProductRepository productRepository = (ProductRepository) context.getBean("productRepository");
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequestDto requestDto) {
        Product product = new Product(requestDto);

        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Long updateProduct(Long id, ProductMyPriceRequestDto requestDto) {
        Optional<Product> byId = productRepository.findById(id);

        if (!byId.isPresent()) {
            throw new NullPointerException("해당 상품은 존재하지 않습니다.");
        }
        Product product = byId.get();
        product.update(requestDto);

        return productRepository.save(product).getId();
    }
}
