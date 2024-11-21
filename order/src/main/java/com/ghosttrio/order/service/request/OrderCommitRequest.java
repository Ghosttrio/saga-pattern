package com.ghosttrio.order.service.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderCommitRequest {
    private Long productId;
    private Long quantity;
}
