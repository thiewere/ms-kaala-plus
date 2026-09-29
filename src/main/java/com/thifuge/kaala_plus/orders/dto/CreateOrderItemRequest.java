package com.thifuge.kaala_plus.orders.dto;

import java.math.BigDecimal;

public record CreateOrderItemRequest(
        Integer supplierProductId,
        Integer quantity,
        BigDecimal unitPrice
) {
}
