package com.payment.response;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Payment_kafka_success")
public class PaymentSuccess_FAILED {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String eventId;

	private String eventType;

	private int orderId;

	private Long customerId;

	private Double amount;

	private String paymentId;

	private String paymentMethod;

	private String paymentStatus;
	
	private String reason;

}
