package com.thifuge.kaala_plus.suppliers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Optional<Supplier> findByName(String name);
    Optional<Supplier> findByPhone(String phone);
}
