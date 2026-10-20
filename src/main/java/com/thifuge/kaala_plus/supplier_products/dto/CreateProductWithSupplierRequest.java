package com.thifuge.kaala_plus.supplier_products.dto;

import java.math.BigDecimal;

public record CreateProductWithSupplierRequest(
        String name,
        String brand,
        String description,
        Integer supplierId,
        BigDecimal unitPrice
) {
}
