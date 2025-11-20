package com.ecommerce.core.payment.application.dto;

public record PaymentCommand(
    String paymentKey,
    String orderId,
    Long amount
) {
}
