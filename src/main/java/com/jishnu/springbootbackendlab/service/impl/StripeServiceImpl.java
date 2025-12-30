package com.jishnu.springbootbackendlab.service.impl;

import com.jishnu.springbootbackendlab.entity.Payment;
import com.jishnu.springbootbackendlab.repository.PaymentRepository;
import com.jishnu.springbootbackendlab.service.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl implements PaymentGateway {

    private final PaymentRepository paymentRepository;

    public StripeServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void processPayment(double amount) {

        Payment payment = new Payment(amount, "USD", "SUCCESS");
        paymentRepository.save(payment);
        System.out.println("Paid " + amount + " using Stripe.");
    }
}
