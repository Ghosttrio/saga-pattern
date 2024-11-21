package com.ghosttrio.stock.api;

import com.ghosttrio.stock.event.SagaEvent;
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
    public void orderCommitConsumer(SagaEvent sagaEvent) {
        log.info("=====> order commit 컨슈머 실행 (5)");
        stockService.decreaseStock(sagaEvent);
    }

    @KafkaListener(topics = "delivery-rollback" , groupId = "group-ghosttrio")
    public void deliveryRollbackConsumer(SagaEvent sagaEvent) {
        log.info("xxxxx> delivery rollback 컨슈머 실행 (2)");
        stockService.rollbackStock(sagaEvent);
    }




}
