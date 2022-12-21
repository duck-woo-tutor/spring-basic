package io.myselectshop.controller;

import io.myselectshop.dto.ProductMyPriceRequestDto;
import io.myselectshop.dto.ProductRequestDto;
import io.myselectshop.dto.ProductResponseDto;
import io.myselectshop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, HttpServletRequest request) {
        return new ProductResponseDto(productService.createProduct(requestDto, request));
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getProducts(HttpServletRequest request) {
        return productService.getProducts(request);
    }

    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMyPriceRequestDto requestDto, HttpServletRequest request) {
        return productService.updateProduct(id, requestDto, request);
    }
}
