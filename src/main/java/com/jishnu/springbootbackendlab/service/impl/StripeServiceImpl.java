package com.jishnu.springbootbackendlab.service.impl;

import com.jishnu.springbootbackendlab.dto.PaymentResponse;
import com.jishnu.springbootbackendlab.entity.Payment;
import com.jishnu.springbootbackendlab.exception.custom.PaymentProcessingException;
import com.jishnu.springbootbackendlab.repository.PaymentRepository;
import com.jishnu.springbootbackendlab.service.PaymentGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StripeServiceImpl implements PaymentGateway {

    private final PaymentRepository paymentRepository;

    public StripeServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void processPayment(double amount) {
        log.info("processPayment - BEGIN");
        if (amount > 1000000) {
            throw new PaymentProcessingException(
                    "This much amount cant be paid at this moment",
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "INTERNAL_SERVER_ERROR"
            );
        }
        Payment payment = new Payment(amount, "USD", "SUCCESS");
        paymentRepository.save(payment);
        System.out.println("Paid " + amount + " using Stripe.");
    }

    @Override
    public List<PaymentResponse> getAllPayments() {
         List<PaymentResponse> paymentResponseList = paymentRepository.findAll()
                .stream()
                .map(payment -> new PaymentResponse(
                        payment.getId(),
                        payment.getAmount(),
                        payment.getCurrency(),
                        payment.getStatus(),
                        payment.getCreatedAt()
                ))
                .toList();

         return paymentResponseList;
    }
}
