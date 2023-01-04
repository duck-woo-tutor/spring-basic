package io.myselectshop.service;

import io.jsonwebtoken.Claims;
import io.myselectshop.config.security.JwtUtil;
import io.myselectshop.dto.ItemDto;
import io.myselectshop.dto.ProductMyPriceRequestDto;
import io.myselectshop.dto.ProductRequestDto;
import io.myselectshop.entity.Folder;
import io.myselectshop.entity.Product;
import io.myselectshop.entity.User;
import io.myselectshop.entity.UserRole;
import io.myselectshop.repository.FolderRepository;
import io.myselectshop.repository.ProductRepository;
import io.myselectshop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final JwtUtil jwtUtil;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final FolderRepository folderRepository;

    public Product createProduct(ProductRequestDto requestDto, HttpServletRequest request) {
        User user = getJwtUser(request);

        Product product = new Product(requestDto, user.getId());

        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Page<Product> getProducts(User user, int page, int size, String sortBy, Boolean isAsc) {
        // Paging
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productList;

        if (user.getRole() == UserRole.USER) {
            productList = productRepository.findAllByUserId(user.getId(), pageable);
        } else {
            productList = productRepository.findAll(pageable);
        }

        return productList;
    }

    public Long updateProduct(Long id, ProductMyPriceRequestDto requestDto, User user) {
        Product product = productRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new NullPointerException("해당 상품은 존재하지 않습니다."));

        product.update(requestDto);
        return productRepository.save(product).getId();
    }

    public void updateBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 상품은 존재하지 않습니다."));
        product.updateByItemDto(itemDto);
    }

    public Product addFolder(Long id, Long folderId, User user) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 상품 아이디가 존재하지 않습니다."));

        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new NullPointerException("해당 폴더 아이디가 존재하지 않습니다."));

        if (!product.getUserId().equals(user.getId()) || !folder.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("회원님의 관심상품이 아니거나, 회원님의 폴더가 아닙니다~^^");
        }

        Optional<Product> overlapFolder = productRepository.findByIdAndFolderList_Id(product.getId(), folder.getId());

        if (overlapFolder.isPresent()) {
            throw new IllegalArgumentException("중복된 폴더입니다.");
        }

        product.addFolder(folder);

        return product;
    }

    private User getJwtUser(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);

        if (token == null) {
            throw new SecurityException("인증 실패");
        }
        Claims claims = jwtUtil.getClaims(token);
        Long userId = Long.parseLong(claims.get("userId").toString());
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
    }
}
