package com.ghosttrio.order.api;

import com.ghosttrio.order.api.request.CreateOrderRequest;
import com.ghosttrio.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public void createOrder(@RequestBody CreateOrderRequest request) {
        log.info("=====> 주문 시작 (1)");
        orderService.order(request.getProductId(), request.getProductName(), request.getQuantity());
    }

}
