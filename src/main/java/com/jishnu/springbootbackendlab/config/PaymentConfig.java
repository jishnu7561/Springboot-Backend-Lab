package com.jishnu.springbootbackendlab.config;

import com.jishnu.springbootbackendlab.repository.PaymentRepository;
import com.jishnu.springbootbackendlab.service.PaymentGateway;
import com.jishnu.springbootbackendlab.service.impl.StripeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {

    @Bean
    public PaymentGateway paymentGateway(PaymentRepository paymentRepository) {
        return new StripeServiceImpl(paymentRepository);
    }
}
