package com.thifuge.kaala_plus.orders.dto;

import java.time.LocalDate;

public record PendingOrderResponse(
        Integer id,
        String reference,
        String clientFirstname,
        String  clientLastname,
        String description,
        LocalDate createdAt
) {
}
