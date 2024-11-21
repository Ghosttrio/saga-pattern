package com.ghosttrio.order.event;

import lombok.Data;

@Data
public class SagaEvent {

    private Long orderId;
    private String status;
}
