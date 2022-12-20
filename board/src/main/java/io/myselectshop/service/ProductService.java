package io.myselectshop.service;

import io.myselectshop.dto.ProductMyPriceRequestDto;
import io.myselectshop.dto.ProductRequestDto;
import io.myselectshop.dto.ProductResponseDto;
import io.myselectshop.entity.Product;
import io.myselectshop.repository.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ApplicationContext context) {
        ProductRepository productRepository = (ProductRepository) context.getBean("productRepository");
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(ProductRequestDto requestDto) throws SQLException {
        Product product = new Product(requestDto);

        return productRepository.createProduct(product);
    }

    public List<ProductResponseDto> getProducts() throws SQLException {
        return productRepository.getProducts();
    }

    public Long updateProduct(Long id, ProductMyPriceRequestDto requestDto) throws SQLException {
        Product product = productRepository.getProduct(id);

        if (product == null) {
            throw new NullPointerException("해당 상품은 존재하지 않습니다.");
        }

        return productRepository.updateProduct(product.getId(), requestDto);
    }
}
