package io.myselectshop.controller;

import io.myselectshop.config.security.UserDetailsImpl;
import io.myselectshop.dto.ProductMyPriceRequestDto;
import io.myselectshop.dto.ProductRequestDto;
import io.myselectshop.dto.ProductResponseDto;
import io.myselectshop.entity.Product;
import io.myselectshop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public Page<Product> getProducts(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam Boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return productService.getProducts(userDetails.user(), page - 1, size, sortBy, isAsc);
    }

    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id,
                              @RequestBody ProductMyPriceRequestDto requestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return productService.updateProduct(id, requestDto, userDetails.user());
    }

    @PostMapping("/products/{id}/folder")
    public Long addFolder(@PathVariable Long id, @RequestParam Long folderId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Product product = productService.addFolder(id, folderId, userDetails.user());
        return product.getId();
    }
}
