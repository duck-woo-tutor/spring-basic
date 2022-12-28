package io.myselectshop.entity;

import io.myselectshop.dto.ItemDto;
import io.myselectshop.dto.ProductMyPriceRequestDto;
import io.myselectshop.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Product extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    @Column(nullable = false)
    private Long userId;

    @ManyToMany
    private List<Folder> folderList = new ArrayList<>();

    public Product() {
    }

    public Product(ProductRequestDto requestDto, Long userId) {
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myprice = 0;
        this.userId = userId;
    }

    public void update(ProductMyPriceRequestDto requestDto) {
        this.myprice = requestDto.getMyPrice();
    }

    public void updateByItemDto(ItemDto itemDto) {
        this.lprice = itemDto.getLprice();
    }

    public void addFolder(Folder folder) {
        this.folderList.add(folder);
    }
}
