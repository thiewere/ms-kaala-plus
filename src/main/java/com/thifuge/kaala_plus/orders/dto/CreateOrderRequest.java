package com.thifuge.kaala_plus.orders.dto;

public record CreateOrderRequest(
        Integer clientId,
        String currency,
        String description
) {
}
