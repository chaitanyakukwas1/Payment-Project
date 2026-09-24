package com.payment.kafka.service;

import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;

import com.payment.kafka.event.EventResponse;
import com.payment.service.PaymentService;


@Service
public class KafkaConsumer {

    private final PaymentService paymentService;

    public KafkaConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RetryableTopic(
        attempts = "4",
        backOff = @BackOff(
            delay = 2000,
            multiplier = 2.0,
            maxDelay = 10000
        )
    )
    @KafkaListener(
        topics = "order-created",
        groupId = "payment-service-group"
    )
    public void consume(EventResponse eventResponse) {

        System.out.println("=================================");
        System.out.println("Payment Consumer received message");
        System.out.println("Order ID : " + eventResponse.getOrderId());
        System.out.println("Customer ID : " + eventResponse.getCustomerId());

        String paymentProcessing =
                paymentService.paymentProcessing(eventResponse);

        System.out.println("Payment Result : " + paymentProcessing);

        System.out.println("=================================");
    }

    @DltHandler
    public void handleDlt(EventResponse eventResponse) {

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("MESSAGE MOVED TO DLT");
        System.out.println("Order ID : " + eventResponse.getOrderId());
        System.out.println("Customer ID : " + eventResponse.getCustomerId());
        System.out.println("Amount : " + eventResponse.getAmount());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}