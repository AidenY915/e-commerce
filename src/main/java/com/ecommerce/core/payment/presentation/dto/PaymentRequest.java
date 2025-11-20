package com.ecommerce.core.payment.presentation.dto;

import com.ecommerce.core.payment.application.dto.PaymentCommand;

public record PaymentRequest(
        String paymentKey,
        String orderId,
        Long amount
) {
    public PaymentCommand toCommand(){
        return new PaymentCommand(paymentKey(), orderId(), amount());
    }

}