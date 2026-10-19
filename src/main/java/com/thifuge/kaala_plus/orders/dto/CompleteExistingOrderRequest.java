package com.thifuge.kaala_plus.orders.dto;

import java.util.List;

public record CompleteExistingOrderRequest(
        List<CreateOrderItemRequest> items
) {
}
