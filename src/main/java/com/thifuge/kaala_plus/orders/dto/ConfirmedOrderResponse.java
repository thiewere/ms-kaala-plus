package com.thifuge.kaala_plus.orders.dto;

import com.thifuge.kaala_plus.orders.OrderState;

import java.math.BigDecimal;

public record ConfirmedOrderResponse(
        String reference,
        String clientFirstname,
        String  clientLastname,
        BigDecimal totalAmount,
        String currency,
        OrderState orderState
) {
}
