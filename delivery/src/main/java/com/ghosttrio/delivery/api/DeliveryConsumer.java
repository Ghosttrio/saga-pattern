package com.ghosttrio.delivery.api;

import com.ghosttrio.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryConsumer {

    private final DeliveryService deliveryService;

    @KafkaListener(topics = "stock-commit" , groupId = "group-ghosttrio")
    public void stockConsumer(Long productId) {
        deliveryService.delivery(productId);
    }

}
