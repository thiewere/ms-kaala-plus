package com.thifuge.kaala_plus.supplier_products.dto;

import java.math.BigDecimal;

public record CreateSupplierProductResponse(
        Integer id,
        Integer productId,
        Integer supplierId,
        BigDecimal unitPrice
) {
}
