package io.myselectshop.controller;

import io.myselectshop.dto.FolderRequestDto;
import io.myselectshop.entity.Folder;
import io.myselectshop.entity.Product;
import io.myselectshop.service.FolderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FolderController {
    private final FolderService folderService;

    @GetMapping("/folders")
    public List<Folder> getFolders(HttpServletRequest request) {
        return folderService.getFolders(request);
    }

    @PostMapping("/folders")
    public List<Folder> addFolders(@RequestBody FolderRequestDto folderRequestDto, HttpServletRequest request) {
        List<String> folderNames = folderRequestDto.getFolderNames();

        return folderService.addFolders(folderNames, request);
    }

    @GetMapping("/folders/{id}/products")
    public Page<Product> getProductsInFolder(@PathVariable Long id, @RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortBy, @RequestParam Boolean isAsc, HttpServletRequest request) {
        return folderService.getProductsInFolder(id, page - 1, size, sortBy, isAsc, request);
    }
}
