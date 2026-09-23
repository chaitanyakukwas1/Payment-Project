package com.payment.kafka.event;

import lombok.Data;

@Data
public class EventResponse {

    private String eventId;

    private String eventType;

    private int orderId;

    private Long customerId;

    private Double amount;

    private String deliveryAddress;
    
    private String paymentStatus;
}
