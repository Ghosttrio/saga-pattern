package com.ghosttrio.order.service;

import com.ghosttrio.order.domain.OrderEntity;
import com.ghosttrio.order.event.SagaEvent;
import com.ghosttrio.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, SagaEvent> kafkaTemplate;

    @Transactional
    public void order(Long productId, String productName, Long quantity) {
        log.info("=====> 주문 저장 실행 (2)");
        OrderEntity orderEntity = OrderEntity.create(productId, productName, quantity);
        OrderEntity result = orderRepository.save(orderEntity);
        log.info("=====> 주문 저장 성공 (3)");
        createOrderProducer(result.getId());
    }

    public void createOrderProducer(Long orderId) {
        log.info("=====> 주문 저장 이벤트 전송 실행 (4)");
        SagaEvent sagaEvent = new SagaEvent();
        sagaEvent.setOrderId(orderId);
        sagaEvent.setStatus("ORDER_CREATED");
        kafkaTemplate.send("order-commit", sagaEvent);
    }


    @Transactional
    public void orderRollback(SagaEvent sagaEvent) {
        log.info("xxxxx> order rollback 작업 실행 (7)");
        orderRepository.deleteById(sagaEvent.getOrderId());
        log.info("xxxxx> order rollback 작업 종료 (8)");
    }
}
