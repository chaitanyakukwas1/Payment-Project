package com.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.response.PaymentSuccess_FAILED;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentSuccess_FAILED, Integer> {
	
	
}
