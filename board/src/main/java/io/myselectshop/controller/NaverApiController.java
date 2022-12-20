package io.myselectshop.controller;

import io.myselectshop.dto.ItemDto;
import io.myselectshop.service.NaverApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NaverApiController {

    private final NaverApiService naverApiService;

    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam String query) throws JSONException {
        return naverApiService.searchItems(query);
    }
}
