package com.payment.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.payment.kafka.event.EventResponse;
import com.payment.service.PaymentService;


@Service
public class KafkaConsumer {
	
	@Autowired
	PaymentService paymentService;
	
	@KafkaListener(topics = "order-created", groupId = "payment-service-group")
	public void consume(EventResponse eventResponse) {
		
		 String paymentProcessing = paymentService.paymentProcessing(eventResponse);
		 System.out.println(paymentProcessing);
	}
}
