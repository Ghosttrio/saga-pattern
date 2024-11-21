package com.ghosttrio.order.api.request;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private Long productId;
    private String productName;
    private Long quantity;
}
