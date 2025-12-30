package com.jishnu.springbootbackendlab.controller;

import com.jishnu.springbootbackendlab.service.PaymentGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentGateway paymentGateway;

    public PaymentController(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @PostMapping("/pay")
    public ResponseEntity<?> pay(@RequestParam("amount") double amount) {
        paymentGateway.processPayment(amount);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
