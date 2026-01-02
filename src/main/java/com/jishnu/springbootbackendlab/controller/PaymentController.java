package com.jishnu.springbootbackendlab.controller;

import com.jishnu.springbootbackendlab.annotation.ValidCurrency;
import com.jishnu.springbootbackendlab.common.ApiResponse;
import com.jishnu.springbootbackendlab.dto.PaymentRequest;
import com.jishnu.springbootbackendlab.dto.PaymentResponse;
import com.jishnu.springbootbackendlab.service.PaymentGateway;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class PaymentController {

    private final PaymentGateway paymentGateway;

    public PaymentController(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @PostMapping("/pay")
    public ResponseEntity<?> pay(
            @Valid @RequestBody PaymentRequest paymentRequest,
            @ValidCurrency @RequestParam String currency
    ) {
        paymentGateway.processPayment(paymentRequest.amount());
        return ResponseEntity.ok(HttpStatus.OK);
    }

        @GetMapping("/history")
        public ResponseEntity<?> getHistory() {
            List<PaymentResponse> allPayments = paymentGateway.getAllPayments();
            ApiResponse<Object> apiResponse = ApiResponse
                    .builder()
                    .httpStatus(HttpStatus.OK.value())
                    .message("SUCCESS")
                    .data(allPayments)
                    .build();

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
}
