package com.ghosttrio.stock.api;

import com.ghosttrio.stock.api.request.OrderCommitRequest;
import com.ghosttrio.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockConsumer {

    private final StockService stockService;

    @KafkaListener(topics = "order-commit" , groupId = "group-ghosttrio")
    public void orderConsumer(OrderCommitRequest request) {
        log.info("=====> 재고 작업 실행 (6)");
        stockService.decreaseStock(request.getProductId(), request.getQuantity());
    }

    @KafkaListener(topics = "delivery-rollback" , groupId = "group-ghosttrio")
    public void deliveryConsumer(Long productId) {
        stockService.rollbackStock(productId);
    }




}
