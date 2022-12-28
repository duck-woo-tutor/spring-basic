package io.myselectshop.repository;

import io.myselectshop.entity.Folder;
import io.myselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUser(User user);

    List<Folder> findAllByUserAndNameIn(User user, List<String> folderNames);
}
