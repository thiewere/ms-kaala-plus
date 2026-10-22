package com.thifuge.kaala_plus.payments.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePaymentRequest(
        String orderReference,
        BigDecimal amount,
        Integer currencyId,
        BigDecimal exchangeRate,
        LocalDate paymentDate,
        String note
) {
}
