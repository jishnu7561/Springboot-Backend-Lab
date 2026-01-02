package com.jishnu.springbootbackendlab.dto;

import java.time.LocalDateTime;

public record PaymentResponse(
        Long paymentId,
        double amount,
        String currency,
        String status,
        LocalDateTime localDateTime
) {
}
