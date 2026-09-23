package com.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.paymentDetails.UserPayment;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserPayment, Integer> {
	
	UserPayment findByCustomerId(Long customerId);
}
