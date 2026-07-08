package com.thifuge.kaala_plus.shared.exceptions;

import java.time.LocalDateTime;

public record ErrorEntity(
        LocalDateTime time,
        int status,
        String code,
        String message
) {
}
