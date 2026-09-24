package com.payment.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.kafka.event.EventResponse;
import com.payment.kafka.service.KafkaProducer;
import com.payment.paymentDetails.UserPayment;
import com.payment.repository.PaymentRepository;
import com.payment.repository.UserRepository;
import com.payment.response.PaymentSuccess_FAILED;

@Service
public class PaymentService {

	@Autowired
	UserRepository repository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	KafkaProducer kafkaProducer;

	public String paymentProcessing(EventResponse eventResponse) {

		// TEST PURPOSE ONLY
		// Customer ID 9999 ke liye intentionally failure create karenge
		if (eventResponse.getCustomerId() == 9999L) {

			System.out.println("TEST FAILURE: Payment processing failed");

			throw new RuntimeException("Payment processing failed intentionally for Retry/DLT testing");
		}

		PaymentSuccess_FAILED success_FAILED = new PaymentSuccess_FAILED();

		if (repository.findByCustomerId(eventResponse.getCustomerId()) != null) {

			UserPayment user = repository.findByCustomerId(eventResponse.getCustomerId());

			success_FAILED.setEventId("PAY-" + eventResponse.getOrderId());
			success_FAILED.setEventType("PAYMENT_SUCCESS");
			success_FAILED.setOrderId(eventResponse.getOrderId());
			success_FAILED.setCustomerId(eventResponse.getCustomerId());
			success_FAILED.setAmount(eventResponse.getAmount());
			success_FAILED.setPaymentId("TXN-" + paymentId());
			success_FAILED.setPaymentMethod("UPI : " + user.getUpiId());
			success_FAILED.setPaymentStatus("SUCCESS");

			eventResponse.setPaymentStatus("SUCCESS");

			kafkaProducer.kafkaMessage("payment-success", eventResponse);

			paymentRepository.save(success_FAILED);

			return "Payment Success";
		}

		success_FAILED.setEventId("PAY-" + eventResponse.getOrderId());
		success_FAILED.setEventType("PAYMENT_FAILED");
		success_FAILED.setOrderId(eventResponse.getOrderId());
		success_FAILED.setCustomerId(eventResponse.getCustomerId());
		success_FAILED.setAmount(eventResponse.getAmount());
		success_FAILED.setPaymentId("TXN-" + paymentId());
		success_FAILED.setPaymentStatus("FAILED");
		success_FAILED.setReason("User Account Not Found");

		return "Payment Failed please add your payment details in http://localhost:8082/userpaymentaccount";
	}

	public int paymentId() {
		Random r = new Random();
		int txnId = r.nextInt(9000) + 1000;
		return txnId;
	}

}
