package com.thifuge.kaala_plus.orders.dto;

import com.thifuge.kaala_plus.orders.OrderState;

public record OrderDetailsResponse(
        Integer id,
        Integer clientId,
        String currency,
        String description,
        OrderState state
) {
}
