package com.thifuge.kaala_plus.payments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Optional<Payment> findFirstByOrderIdOrderByIdAsc(Integer orderId);

    @Query("""
        SELECT COALESCE(SUM(p.convertedAmount), 0)
        FROM Payment p
        WHERE p.order.id = :orderId
    """)
    BigDecimal sumConvertedAmountByOrderId(@Param("orderId")  Integer orderId);

    List<Payment> findByOrderIdOrderByPaymentDateAscIdAsc(Integer orderId);
}
