package com.thifuge.kaala_plus.orders.dto;

import com.thifuge.kaala_plus.orders.OrderState;

public record CreateOrderResponse(
        Integer id,
        String reference,
        OrderState state
) {
}
