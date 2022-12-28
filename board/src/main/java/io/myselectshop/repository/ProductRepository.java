package io.myselectshop.repository;

import io.myselectshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByUserId(Long userId, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findByIdAndUserId(Long id, Long userId);

    Page<Product> findAllByUserIdAndFolderList_Id(Long userId, Long folderId, Pageable pageable);

    Optional<Product> findByIdAndFolderList_Id(Long id, Long folderId);
}
