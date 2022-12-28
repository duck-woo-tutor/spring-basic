package io.myselectshop.service;

import io.jsonwebtoken.Claims;
import io.myselectshop.config.security.JwtUtil;
import io.myselectshop.entity.Folder;
import io.myselectshop.entity.Product;
import io.myselectshop.entity.User;
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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FolderService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    private final FolderRepository folderRepository;

    private final ProductRepository productRepository;

    @Transactional
    public List<Folder> addFolders(List<String> folderNames, HttpServletRequest request) {
        User user = getJwtUser(request);

        List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);

        List<Folder> folderList = new ArrayList<>();

        for (String folderName : folderNames) {
            if (!isExistFolderName(folderName, existFolderList)) {
                Folder folder = new Folder(folderName, user);
                folderList.add(folder);
            }
        }

        return folderRepository.saveAll(folderList);
    }

    public List<Folder> getFolders(HttpServletRequest request) {
        User user = getJwtUser(request);

        return folderRepository.findAllByUser(user);
    }

    public Page<Product> getProductsInFolder(Long id, int page, Integer size, String sortBy, Boolean isAsc, HttpServletRequest request) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        User user = getJwtUser(request);
        return productRepository.findAllByUserIdAndFolderList_Id(user.getId(), id, pageable);
    }

    private boolean isExistFolderName(String folderName, List<Folder> existFolderList) {
        for (Folder existFolder : existFolderList) {
            if (existFolder.getName().equals(folderName)) {
                return true;
            }
        }
        return false;
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
