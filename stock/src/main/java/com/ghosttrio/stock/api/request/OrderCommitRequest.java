package com.ghosttrio.stock.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderCommitRequest {
    private Long productId;
    private Long quantity;
}