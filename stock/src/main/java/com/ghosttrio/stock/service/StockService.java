package com.ghosttrio.stock.service;

import com.ghosttrio.stock.event.SagaEvent;
import com.ghosttrio.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {

    private final StockRepository stockRepository;
    private final KafkaTemplate<String, SagaEvent> kafkaTemplate;

    @Transactional
    public void decreaseStock(SagaEvent sagaEvent) {
        log.info("=====> 재고 작업 실행 (6)");
        // 재고 작업 코드
        log.info("=====> 재고 작업 종료 (7)");
        createStockProducer(sagaEvent);
    }

    public void createStockProducer(SagaEvent sagaEvent) {
        log.info("=====> 재고 저장 이벤트 전송 실행 (8)");
        sagaEvent.setStatus("STOCK_CREATED");
        kafkaTemplate.send("stock-commit", sagaEvent);
    }

    @Transactional
    public void rollbackStock(SagaEvent sagaEvent) {
        log.info("xxxxx> stock 롤백 실행 (3)");
        // 롤백하는 코드
        log.info("xxxxx> stock 롤백 종료 (4)");

        rollbackStockProducer(sagaEvent);
    }

    public void rollbackStockProducer(SagaEvent sagaEvent) {
        sagaEvent.setStatus("STOCK_ROLLBACK");
        log.info("xxxxx> stock rollback 이벤트 실행 (5)");
        kafkaTemplate.send("stock-rollback", sagaEvent);
    }

}
