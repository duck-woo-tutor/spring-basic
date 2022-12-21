package io.myselectshop.service;

import io.jsonwebtoken.Claims;
import io.myselectshop.config.security.JwtUtil;
import io.myselectshop.dto.ItemDto;
import io.myselectshop.dto.ProductMyPriceRequestDto;
import io.myselectshop.dto.ProductRequestDto;
import io.myselectshop.dto.ProductResponseDto;
import io.myselectshop.entity.Product;
import io.myselectshop.entity.User;
import io.myselectshop.entity.UserRole;
import io.myselectshop.repository.ProductRepository;
import io.myselectshop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public Product createProduct(ProductRequestDto requestDto, HttpServletRequest request) {
        User user = getUser(request);

        Product product = new Product(requestDto, user.getId());

        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProducts(HttpServletRequest request) {
        User user = getUser(request);

        List<ProductResponseDto> result = new ArrayList<>();
        List<Product> productList;

        if (user.getRole() == UserRole.USER) {
            productList = productRepository.findAllByUserId(user.getId());
        } else {
            productList = productRepository.findAll();
        }

        for (Product product : productList) {
            result.add(new ProductResponseDto(product));
        }

        return result;
    }

    public Long updateProduct(Long id, ProductMyPriceRequestDto requestDto, HttpServletRequest request) {
        User user = getUser(request);

        Product product = productRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new NullPointerException("해당 상품은 존재하지 않습니다."));

        product.update(requestDto);
        return productRepository.save(product).getId();
    }

    public void updateBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 상품은 존재하지 않습니다."));
        product.updateByItemDto(itemDto);
    }

    private User getUser(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);

        if (token == null) {
            throw new SecurityException("인증 실패");
        }
        Claims claims = jwtUtil.getClaims(token);
        Long userId = Long.parseLong(claims.get("userId").toString());
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
    }
}
