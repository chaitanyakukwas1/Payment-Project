package com.payment.controller;

import com.payment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payment.paymentDetails.UserPayment;
import com.payment.repository.PaymentRepository;

@RestController
public class PaymentController {
	
	@Autowired
	UserRepository userRepository;
	
	
	@PostMapping("/userpaymentaccount")
	public String createPaymentDetails(@RequestBody UserPayment payment) {
		
		userRepository.save(payment);
		return "Account Details Saved Successfully";
		
	}
	
}
