package io.myselectshop.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

@Getter
@NoArgsConstructor
public class ItemDto {
    private String title;
    private String link;
    private String image;
    private int price;

    public ItemDto(JSONObject itemJson) throws JSONException {
        this.title = itemJson.getString("title");
        this.link =  itemJson.getString("link");
        this.image = itemJson.getString("image");
        this.price = itemJson.getInt("lprice");
    }
}
