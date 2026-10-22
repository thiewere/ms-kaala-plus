package com.thifuge.kaala_plus.payments.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderPaymentSummaryResponse(
        String orderReference,
        BigDecimal orderTotal,
        BigDecimal totalPaid,
        BigDecimal remainingAmount,
        String currency,
        List<CreatePaymentResponse> payments
) {
}
