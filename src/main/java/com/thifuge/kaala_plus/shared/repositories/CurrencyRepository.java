package com.thifuge.kaala_plus.shared.repositories;

import com.thifuge.kaala_plus.shared.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    boolean existsByNameIgnoreCase(String name);
    Optional<Currency> findByName(String name);
}
