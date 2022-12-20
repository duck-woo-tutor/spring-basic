package io.myselectshop.controller;

import io.myselectshop.dto.ProductMyPriceRequestDto;
import io.myselectshop.dto.ProductRequestDto;
import io.myselectshop.dto.ProductResponseDto;
import io.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) throws SQLException {
        return productService.createProduct(requestDto);
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getProducts() throws SQLException {
        return productService.getProducts();
    }

    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMyPriceRequestDto requestDto) throws SQLException {
        return productService.updateProduct(id, requestDto);
    }
}
