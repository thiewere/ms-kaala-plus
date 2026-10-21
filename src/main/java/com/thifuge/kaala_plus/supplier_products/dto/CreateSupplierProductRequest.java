package com.thifuge.kaala_plus.supplier_products.dto;

import java.math.BigDecimal;

public record CreateSupplierProductRequest(
        Integer productId,
        Integer supplierId,
        BigDecimal unitPrice,
        String currency
) {
}
