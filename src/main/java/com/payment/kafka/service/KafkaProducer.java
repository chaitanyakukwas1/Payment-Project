package com.payment.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.payment.kafka.event.EventResponse;

@Service
public class KafkaProducer {
	
	@Autowired
	KafkaTemplate<String, EventResponse> kafkatemplate;
	
	public void kafkaMessage(String _topic, EventResponse eventResponse) {
		
		String key = eventResponse.getOrderId()+"";
		
		kafkatemplate.send(_topic, key,eventResponse);
	}
}
