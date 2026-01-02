package com.jishnu.springbootbackendlab.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PaymentRequest(
        @NotNull(message = "Amount is required")
        @DecimalMin(value = "1", message = "Minimum payment is 1")
        double amount,

        @NotEmpty(message = "Must not be empty")
        String currency
) {
}
