package com.thifuge.kaala_plus.orders.dto;

import java.util.List;

public record CreateCompleteOrderRequest(
        Integer clientId,
        String currency,
        String description,
        List<CreateOrderItemRequest> items
) {

}
