package io.myselectshop.service;

import io.myselectshop.dto.ItemDto;
import io.myselectshop.dto.ProductMyPriceRequestDto;
import io.myselectshop.dto.ProductRequestDto;
import io.myselectshop.entity.Product;
import io.myselectshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductRequestDto requestDto) {
        Product product = new Product(requestDto);

        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
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

    public void updateBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 상품은 존재하지 않습니다.")
        );
        product.updateByItemDto(itemDto);
    }
}
