package com.jishnu.springbootbackendlab.exception.custom;

import lombok.Getter;

@Getter
public class PaymentProcessingException extends RuntimeException {

    private final int status;
    private final String errorCode;

    public PaymentProcessingException(String message, int status, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }
}
