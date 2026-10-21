package com.thifuge.kaala_plus.supplier_products.dto;

import java.math.BigDecimal;

public record CreateProductAndSupplierRequest(
        String name,
        String brand,
        String description,
        String supplierName,
        String supplierPhone,
        String supplierAddress,
        BigDecimal unitPrice,
        String currency
) {
}
