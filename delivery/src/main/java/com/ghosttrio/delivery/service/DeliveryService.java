package com.ghosttrio.delivery.service;

import com.ghosttrio.delivery.event.SagaEvent;
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
    private final KafkaTemplate<String, SagaEvent> kafkaTemplate;

    @Transactional
    public void delivery(SagaEvent sagaEvent) {
        try {
            log.info("=====> 배달 작업 시작 (10)");
            error(); // 에러를 일으킬만한 코드
            // 배달 작업 코드
            log.info("=====> 배달 작업 종료 (11)");
        } catch (RuntimeException e) {
            log.info("xxxxx> 배달 작업에서 롤백 실행 (1)");
            sagaEvent.setStatus("DELIVERY_ROLLBACK");
            kafkaTemplate.send("delivery-rollback", sagaEvent);
        }
    }

    private void error() {
        int zeroOrOne = new Random().nextInt(2);

        if (zeroOrOne == 0) {
            throw new RuntimeException();
        }
    }

}
