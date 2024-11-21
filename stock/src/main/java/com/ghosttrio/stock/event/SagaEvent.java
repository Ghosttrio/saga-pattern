package com.ghosttrio.stock.event;

import lombok.Data;

@Data
public class SagaEvent {

    private Long orderId;
    private String status;
}
