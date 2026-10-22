package com.thifuge.kaala_plus.orders;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findByReference(String reference);

    List<Order> findByOrderStateOrderByCreatedAtAsc(OrderState state);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT o FROM Order o WHERE o.reference = :reference")
    Optional<Order> findByReferenceForUpdate(@Param("reference") String reference);
}
