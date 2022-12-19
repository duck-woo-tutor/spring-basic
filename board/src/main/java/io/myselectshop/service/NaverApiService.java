package io.myselectshop.service;

import io.myselectshop.controller.dto.ItemDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class NaverApiService {

    @Value(value = "${external-api.naver.X-Naver-Client-Id}")
    private String id;

    @Value(value = "${external-api.naver.X-Naver-Client-Secret}")
    private String secret;

    public List<ItemDto> searchItems(String query) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", id);
        headers.add("X-Naver-Client-Secret", secret);

        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange("https://openapi.naver.com/v1/search/shop.json?display=15&query=" + query
                        , HttpMethod.GET, requestEntity, String.class);

        HttpStatusCode httpStatusCode = responseEntity.getStatusCode();
        int statusCode = httpStatusCode.value();
        log.info("Naver Api Status Code : " + statusCode);

        String response = responseEntity.getBody();

        return fromJSONtoItems(response);
    }

    private List<ItemDto> fromJSONtoItems(String response) throws JSONException {
        JSONObject json = new JSONObject(response);
        JSONArray items = json.getJSONArray("items");

        List<ItemDto> itemDtoList = new ArrayList<>();
        for(int i = 0; i < items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(itemJson);
            itemDtoList.add(itemDto);
        }

        return itemDtoList;
    }
}
