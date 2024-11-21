package com.ghosttrio.stock.service;

import com.ghosttrio.stock.domain.Stock;
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
    private final KafkaTemplate<String, Long> kafkaTemplate;

    @Transactional
    public void decreaseStock(Long productId, Long quantity) {
        Stock stock = stockRepository.findByProductId(productId).orElseThrow(RuntimeException::new);
        stock.decrease(quantity);
        createStockProducer(productId);
    }

    public void createStockProducer(Long productId) {
        kafkaTemplate.send("stock-commit", productId);
    }

    @Transactional
    public void rollbackStock(Long productId) {
        stockRepository.deleteByProductId(productId);
        rollbackStockProducer(productId);
    }

    public void rollbackStockProducer(Long productId) {
        kafkaTemplate.send("stock-rollback", productId);
    }

}
