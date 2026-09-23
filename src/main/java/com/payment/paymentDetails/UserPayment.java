package com.payment.paymentDetails;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_kafka_payment")
@Data
public class UserPayment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Long customerId;
	private Long accNo;
	private String firstName;
	private String lastName;
	private String upiId;
	private int transactionPin;
	
	
}
