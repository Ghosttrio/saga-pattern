package com.ghosttrio.order.api;

import com.ghosttrio.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = "stock-rollback" , groupId = "group-ghosttrio")
    public void orderConsumer(Long productId) {
        orderService.orderRollback(productId);
    }
}


