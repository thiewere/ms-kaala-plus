package com.thifuge.kaala_plus.supplier_products.dto;

import java.math.BigDecimal;

public record CreateSupplierWithProductRequest(
        Integer productId,
        String supplierName,
        String supplierPhone,
        String supplierAddress,
        BigDecimal unitPrice,
        String currency
) {
}
