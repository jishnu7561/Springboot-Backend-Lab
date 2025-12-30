package com.jishnu.springbootbackendlab.service;

public interface PaymentGateway {

    void processPayment(double amount);
}
