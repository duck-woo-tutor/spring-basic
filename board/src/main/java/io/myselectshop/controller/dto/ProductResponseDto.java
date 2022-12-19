package io.myselectshop.controller.dto;

import io.myselectshop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String title;
    private String image;
    private String link;
    private Integer lprice;
    private Integer myprice;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.image = product.getImage();
        this.link = product.getLink();
        this.lprice = product.getLprice();
        this.myprice = product.getMyprice();
    }
}
