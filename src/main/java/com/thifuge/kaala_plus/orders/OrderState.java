package com.thifuge.kaala_plus.orders;

public enum OrderState {
    INITIATED,
    READY_FOR_CONFIRMATION,
    CONFIRMED,
    IN_PROGRESS,
    PAID,
    COMPLETED,
    CANCELED
}
