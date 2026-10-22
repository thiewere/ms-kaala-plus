package com.thifuge.kaala_plus.payments.dto;

import com.thifuge.kaala_plus.payments.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePaymentResponse(
        Integer id,
        String orderReference,
        BigDecimal amount,
        String currency,
        BigDecimal exchangeRate,
        BigDecimal convertedAmount,
        PaymentType paymentType,
        LocalDate paymentDate,
        String note
) {
}
