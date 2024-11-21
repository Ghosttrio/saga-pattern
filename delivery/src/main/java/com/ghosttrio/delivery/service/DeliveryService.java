package com.ghosttrio.delivery.service;

import com.ghosttrio.delivery.domain.Delivery;
import com.ghosttrio.delivery.repository.DeliveryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final KafkaTemplate<String, Long> kafkaTemplate;

    @Transactional
    public void delivery(Long productId) {
        try {
            error();
            Delivery delivery = Delivery.create(productId);
            deliveryRepository.save(delivery);
        } catch (RuntimeException e) {
            kafkaTemplate.send("delivery-rollback", productId);
        }
    }

    private void error() {
        int zeroOrOne = new Random().nextInt(2);

        if (zeroOrOne == 0) {
            throw new RuntimeException();
        }
    }

}
