package io.myselectshop.demon;

import io.myselectshop.dto.ItemDto;
import io.myselectshop.entity.Product;
import io.myselectshop.repository.ProductRepository;
import io.myselectshop.service.NaverApiService;
import io.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {
    private final NaverApiService naverApiService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Scheduled(cron = "0 0 1 * * *")
    public void updatePrice() throws InterruptedException {
        log.info("가격 업데이트 실행");
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            TimeUnit.SECONDS.sleep(1);

            String title = product.getTitle();
            List<ItemDto> itemDtoList = naverApiService.searchItems(title);
            ItemDto itemDto = itemDtoList.get(0);

            Long id = product.getId();
            productService.updateBySearch(id, itemDto);
        }
    }

//    @Scheduled(fixedDelay = 1000)
    public void scheduleCheck() throws InterruptedException {
        log.info("schedule 아~ 1초마다 돌아라!");
    }
}
