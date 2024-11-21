package com.ghosttrio.order.service;

import com.ghosttrio.order.domain.Order;
import com.ghosttrio.order.repository.OrderRepository;
import com.ghosttrio.order.service.request.OrderCommitRequest;
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
    private final KafkaTemplate<String, OrderCommitRequest> kafkaTemplate;

    @Transactional
    public void order(Long productId, String productName, Long quantity) {
        log.info("=====> 주문 저장 실행 (2)");
        Order order = Order.create(productId, productName, quantity);
        Order result = orderRepository.save(order);
        log.info("=====> 주문 저장 성공 (3)");
        createOrderProducer(new OrderCommitRequest(result.getId(), result.getQuantity()));
    }

    public void createOrderProducer(OrderCommitRequest request) {
        log.info("=====> 주문 저장 이벤트 전송 실행 (4)");
        kafkaTemplate.send("order-commit", request);
        log.info("=====> 주문 저장 이벤트 전송 완료 (5)");
    }

    @Transactional
    public void orderRollback(Long productId) {
        orderRepository.deleteByProductId(productId);
    }
}
