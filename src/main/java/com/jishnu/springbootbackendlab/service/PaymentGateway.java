package com.jishnu.springbootbackendlab.service;

import com.jishnu.springbootbackendlab.dto.PaymentResponse;

import java.util.List;

public interface PaymentGateway {

    void processPayment(double amount);

    List<PaymentResponse> getAllPayments();
}
